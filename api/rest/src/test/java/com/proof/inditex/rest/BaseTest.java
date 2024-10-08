package com.proof.inditex.rest;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import com.proof.inditex.domain.shared.Price;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

  protected Price createPrice(String startDate, String endDate) {
    return Price.builder()
        .id(1L)
        .brandId(1L)
        .date(LocalDateTime.now())
        .priceList(1L)
        .price("10.00")
        .endDate(endDate == null ? null : LocalDateTime.parse(endDate))
        .startDate(startDate == null ? null : LocalDateTime.parse(startDate))
        .build();
  }

  protected FindPriceRequestDTO createFindPriceRequestDTO() {
    return FindPriceRequestDTO.builder()
        .productId(1L)
        .brandId(1L)
        .date(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        .build();
  }

  protected PriceDTO createPriceDTO(Long productId, Long brandId, String price,
      Long priceList, String dateStart, String dateEnd) {
    return PriceDTO.builder()
        .productId(productId)
        .brandId(brandId)
        .price(price)
        .priceId(priceList.intValue())
        .dateStart(dateStart)
        .dateEnd(dateEnd)
        .build();
  }
}
