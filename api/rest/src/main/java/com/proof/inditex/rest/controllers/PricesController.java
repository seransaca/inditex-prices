package com.proof.inditex.rest.controllers;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import com.proof.inditex.api.web.api.PriceApi;
import com.proof.inditex.rest.helpers.FindPriceHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PricesController implements PriceApi {

  private FindPriceHelper findPriceHelper;

  @Override
  public ResponseEntity<PriceDTO> findPrice(FindPriceRequestDTO findPriceRequest) {
    return findPriceHelper.findPrice(findPriceRequest);
  }

}
