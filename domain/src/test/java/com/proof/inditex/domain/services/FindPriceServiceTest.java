package com.proof.inditex.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.proof.inditex.domain.entities.PricesEntity;
import com.proof.inditex.domain.mappers.DomainPriceMapper;
import com.proof.inditex.domain.repositories.PricePort;
import com.proof.inditex.domain.shared.Price;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FindPriceServiceTest {

  @Mock
  private PricePort pricePort;

  @Mock
  private DomainPriceMapper domainPriceMapper;

  @InjectMocks
  private FindPriceService findPriceService;

  @Test
  public void testFindPrice() {

    PricesEntity entity = createPriceEntity();
    Price price = createPrice();

    when(pricePort.findPrice(anyLong(), anyLong(), any(LocalDateTime.class)))
        .thenReturn(entity);

    when(domainPriceMapper.mapProductEntityToProduct(any(PricesEntity.class),
        any(LocalDateTime.class)))
        .thenReturn(price);

    Price result = findPriceService.findPrice(price);

    assertEquals(price, result);

  }

  private PricesEntity createPriceEntity() {
    return PricesEntity.builder()
        .id(1L)
        .brandId(1L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusHours(2))
        .priceList(1)
        .priority(1)
        .price(10.00)
        .curr("EUR")
        .build();
  }

  private Price createPrice() {
    return Price.builder()
        .id(1L)
        .brandId(1L)
        .date(LocalDateTime.now())
        .priceList(1L)
        .price("10.00")
        .endDate(LocalDateTime.now().plusHours(2))
        .startDate(LocalDateTime.now())
        .build();
  }
}
