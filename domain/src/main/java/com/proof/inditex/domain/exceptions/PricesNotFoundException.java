package com.proof.inditex.domain.exceptions;

import java.time.LocalDateTime;
import org.springframework.core.NestedRuntimeException;

public class PricesNotFoundException extends NestedRuntimeException {

  public PricesNotFoundException(Long productId, Long brandId, LocalDateTime date) {
    super("No prices found for the given criteria: productId=" + productId + ", brandId=" + brandId
        + ", date=" + date);
  }
}
