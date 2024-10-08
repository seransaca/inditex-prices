package com.proof.inditex.domain.services;


import com.proof.inditex.domain.entities.PricesEntity;
import com.proof.inditex.domain.mappers.DomainPriceMapper;
import com.proof.inditex.domain.repositories.PricePort;
import com.proof.inditex.domain.shared.Price;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindPriceService {

  private PricePort pricePort;
  private DomainPriceMapper domainPriceMapper;

  public Price findPrice(Price price) {

    PricesEntity pricesEntity = pricePort.findPrice(price.getId(), price.getBrandId(),
        price.getDate());

    return domainPriceMapper.mapProductEntityToProduct(pricesEntity, price.getDate());

  }
}
