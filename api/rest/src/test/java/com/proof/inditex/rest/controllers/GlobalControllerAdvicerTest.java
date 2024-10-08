package com.proof.inditex.rest.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.proof.inditex.api.dto.ErrorResponseDTO;
import com.proof.inditex.domain.exceptions.BrandNotFoundException;
import com.proof.inditex.domain.exceptions.PricesNotFoundException;
import com.proof.inditex.domain.exceptions.ProductNotFoundException;
import com.proof.inditex.rest.controllers.exceptions.GlobalControllerAdvicer;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ExtendWith(MockitoExtension.class)
public class GlobalControllerAdvicerTest {

  @InjectMocks
  private GlobalControllerAdvicer controllerAdvicer;

  @Mock
  private MockHttpServletRequest request;

  @Test
  public void testValidationException_ValidatesAndReturnsError() throws NoSuchMethodException {
    BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "objectName");
    bindingResult.addError(new FieldError("objectName", "field1", "message"));
    bindingResult.addError(new FieldError("objectName", "field2", "message"));
    Method method = this.getClass()
        .getMethod("testValidationException_ValidatesAndReturnsError", (Class<?>[]) null);
    MethodParameter parameter = new MethodParameter(method, -1);
    MethodArgumentNotValidException exception =
        new MethodArgumentNotValidException(parameter, bindingResult);

    final ResponseEntity<ErrorResponseDTO> response =
        controllerAdvicer.validationErrorException(exception);

    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void testMethodArgumentTypeMismatchException() {
    MethodArgumentTypeMismatchException ex = mock(
        MethodArgumentTypeMismatchException.class);
    when(ex.getName()).thenReturn("id");
    when(ex.getValue()).thenReturn("invalidValue");

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.methodArgumentTypeMismatchException(
        ex);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    MatcherAssert.assertThat(responseEntity.getBody().getMessage(),
        containsString("El campo 'id' con el valor 'invalidValue' es incorrecto"));
  }

  @Test
  public void testMissingServletRequestParameterException() {
    MissingServletRequestParameterException ex = mock(
        MissingServletRequestParameterException.class);
    when(ex.getParameterName()).thenReturn("paramName");

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.missingServletRequestParameterException(
        ex);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    MatcherAssert.assertThat(responseEntity.getBody().getMessage(),
        containsString("Se esperaba el parámetro paramName"));
  }

  @Test
  public void testHttpMessageNotReadableException() {
    HttpMessageNotReadableException ex = mock(HttpMessageNotReadableException.class);

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.httpMessageNotReadable(ex);

    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertEquals("JSON mal formado", responseEntity.getBody().getMessage());
  }

  @Test
  public void testPricesNotFoundException() {
    PricesNotFoundException ex = new PricesNotFoundException(35455L, 1L, LocalDateTime.now());

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.handlePricesNotFound(ex);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals(ex.getMessage(), responseEntity.getBody().getMessage());
  }

  @Test
  public void testProductNotFoundException() {
    ProductNotFoundException ex = new ProductNotFoundException(35455L);

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.handleProductNotFound(ex);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals(ex.getMessage(), responseEntity.getBody().getMessage());
  }

  @Test
  public void testBrandNotFoundException() {
    BrandNotFoundException ex = new BrandNotFoundException(1L);

    ResponseEntity<ErrorResponseDTO> responseEntity = controllerAdvicer.handleBrandNotFound(ex);

    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertEquals(ex.getMessage(), responseEntity.getBody().getMessage());
  }
}

