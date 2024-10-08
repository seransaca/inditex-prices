package com.proof.inditex.domain.mappers;

import com.proof.inditex.domain.commons.ProductMapperConfig;
import com.proof.inditex.domain.entities.PricesEntity;
import com.proof.inditex.domain.shared.Price;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = ProductMapperConfig.class)
public interface DomainPriceMapper {

  @Mapping(source = "pricesEntity.productId", target = "id")
  @Mapping(source = "pricesEntity", target = "price", qualifiedByName = "priceFormated")
  Price mapProductEntityToProduct(PricesEntity pricesEntity, LocalDateTime date);

  @Named("priceFormated")
  default String priceFormated(PricesEntity product) {
    return String.format("%.2f", product.getPrice()) + " " + product.getCurr();
  }

}
