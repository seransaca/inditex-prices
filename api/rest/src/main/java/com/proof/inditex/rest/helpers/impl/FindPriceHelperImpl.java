package com.proof.inditex.rest.helpers.impl;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import com.proof.inditex.domain.services.FindPriceService;
import com.proof.inditex.domain.shared.Price;
import com.proof.inditex.rest.helpers.FindPriceHelper;
import com.proof.inditex.rest.mappers.RestPriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindPriceHelperImpl implements FindPriceHelper {

  private RestPriceMapper restPriceMapper;
  private FindPriceService findPriceService;

  @Override
  public ResponseEntity<PriceDTO> findPrice(FindPriceRequestDTO findProductRequest) {

    Price price = restPriceMapper.mapDTOToPrice(findProductRequest);

    Price priceReturned = findPriceService.findPrice(price);

    return ResponseEntity.ok(restPriceMapper.mapPriceToPriceDTO(priceReturned));
  }
}
