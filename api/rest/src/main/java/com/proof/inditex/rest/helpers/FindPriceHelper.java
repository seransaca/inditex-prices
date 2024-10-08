package com.proof.inditex.rest.helpers;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import org.springframework.http.ResponseEntity;

public interface FindPriceHelper {

  ResponseEntity<PriceDTO> findPrice(FindPriceRequestDTO findPriceRequest);

}
