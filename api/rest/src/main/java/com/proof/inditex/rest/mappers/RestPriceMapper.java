package com.proof.inditex.rest.mappers;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import com.proof.inditex.domain.commons.ProductMapperConfig;
import com.proof.inditex.domain.shared.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = ProductMapperConfig.class)
public interface RestPriceMapper {

  @Mapping(source = "productId", target = "id")
  @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd HH:mm")
  @Mapping(target = "startDate", ignore = true)
  @Mapping(target = "endDate", ignore = true)
  @Mapping(target = "priceList", ignore = true)
  @Mapping(target = "price", ignore = true)
  Price mapDTOToPrice(FindPriceRequestDTO findPriceRequestDTO);

  @Mapping(source = "id", target = "productId")
  @Mapping(source = "priceList", target = "priceId")
  @Mapping(source = "startDate", target = "dateStart", dateFormat = "yyyy-MM-dd HH:mm:ss")
  @Mapping(source = "endDate", target = "dateEnd", dateFormat = "yyyy-MM-dd HH:mm:ss")
  PriceDTO mapPriceToPriceDTO(Price price);

}
