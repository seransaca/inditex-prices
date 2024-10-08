package com.proof.inditex.domain.exceptions;

import java.time.LocalDateTime;
import org.springframework.core.NestedRuntimeException;

public class ProductNotFoundException extends NestedRuntimeException {

  public ProductNotFoundException(Long productId) {
    super("The product with id " + productId + " was not found");
  }
}
