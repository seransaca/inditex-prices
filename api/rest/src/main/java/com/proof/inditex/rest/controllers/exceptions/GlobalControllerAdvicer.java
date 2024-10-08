package com.proof.inditex.rest.controllers.exceptions;

import com.proof.inditex.api.dto.ErrorResponseDTO;
import com.proof.inditex.api.dto.ValidationDTO;
import com.proof.inditex.domain.exceptions.BrandNotFoundException;
import com.proof.inditex.domain.exceptions.PricesNotFoundException;
import com.proof.inditex.domain.exceptions.ProductNotFoundException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalControllerAdvicer {

  private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvicer.class);
  private static final String CORE_INVALID_PARAMS = "Los siguientes parámetros no cumplen los requisitos especificados: %s";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> validationErrorException(
      MethodArgumentNotValidException ex) {
    logger.warn("Error de validación: {}", ex.getBindingResult().getAllErrors());
    String errorMessage = String.format(CORE_INVALID_PARAMS,
        getFieldsInError(ex.getBindingResult()));

    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message(errorMessage)
        .cause(Objects.isNull(ex.getCause()) ? null : ex.getCause().getMessage())
        .validations(getValidations(ex.getBindingResult()))
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponseDTO> methodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException ex) {
    String msg =
        String.format("El campo '%s' con el valor '%s' es incorrecto", ex.getName(), ex.getValue());
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message(msg)
        .cause(Objects.isNull(ex.getCause()) ? null : ex.getCause().getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorResponseDTO> missingServletRequestParameterException(
      MissingServletRequestParameterException ex) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message("Se esperaba el parámetro " + ex.getParameterName())
        .cause(Objects.isNull(ex.getCause()) ? null : ex.getCause().getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponseDTO> httpMessageNotReadable(
      HttpMessageNotReadableException ex) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message("JSON mal formado")
        .cause(Objects.isNull(ex.getCause()) ? null : ex.getCause().getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PricesNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handlePricesNotFound(PricesNotFoundException ex) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message(ex.getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ProductNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleProductNotFound(ProductNotFoundException ex) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message(ex.getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BrandNotFoundException.class)
  public ResponseEntity<ErrorResponseDTO> handleBrandNotFound(BrandNotFoundException ex) {
    ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
        .message(ex.getMessage())
        .build();
    return buildResponseEntity(errorResponse, HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<ErrorResponseDTO> buildResponseEntity(ErrorResponseDTO errorResponse,
      HttpStatus httpStatus) {
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

  private List<ValidationDTO> getValidations(BindingResult bindingResult) {
    return bindingResult.getFieldErrors()
        .stream()
        .map(error -> ValidationDTO.builder()
            .dto(error.getObjectName())
            .field(error.getField())
            .error(error.getDefaultMessage())
            .build())
        .toList();
  }

  private String getFieldsInError(BindingResult bindingResult) {
    return bindingResult.getFieldErrors()
        .stream()
        .map(FieldError::getField)
        .distinct()
        .reduce((fields, fieldName) -> fields.concat(",").concat(fieldName))
        .orElse("");
  }


}
