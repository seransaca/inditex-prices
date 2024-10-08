package com.proof.inditex.domain.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.proof.inditex.domain.entities.PricesEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DomainPriceMapperTest {

  private DomainPriceMapper domainPriceMapper = new DomainPriceMapperImpl();

  @Test
  public void priceFormated() {
    // Given
    PricesEntity entity = PricesEntity.builder()
        .price(10.00)
        .curr("EUR")
        .build();

    // When
    String price = domainPriceMapper.priceFormated(entity);

    // Then
    assertEquals("10,00 EUR", price);
  }

}
