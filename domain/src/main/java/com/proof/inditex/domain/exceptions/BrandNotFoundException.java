package com.proof.inditex.domain.exceptions;

import org.springframework.core.NestedRuntimeException;

public class BrandNotFoundException extends NestedRuntimeException {

  public BrandNotFoundException(Long brandId) {
    super("The brand with id " + brandId + " was not found");
  }
}
