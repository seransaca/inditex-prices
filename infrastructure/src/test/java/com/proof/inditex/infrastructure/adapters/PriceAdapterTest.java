package com.proof.inditex.infrastructure.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.proof.inditex.domain.entities.PricesEntity;
import com.proof.inditex.domain.exceptions.BrandNotFoundException;
import com.proof.inditex.domain.exceptions.PricesNotFoundException;
import com.proof.inditex.domain.exceptions.ProductNotFoundException;
import com.proof.inditex.infrastructure.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PriceAdapterTest {

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private PriceAdapter priceAdapter;

  @Test
  public void testFindPrice() {
    List<PricesEntity> list = new ArrayList<>();
    PricesEntity entity = PricesEntity.builder()
        .productId(1L)
        .brandId(1L)
        .startDate(LocalDateTime.now())
        .endDate(LocalDateTime.now().plusHours(2))
        .priceList(1)
        .priority(1)
        .price(10.00)
        .curr("EUR")
        .build();
    list.add(entity);

    when(priceRepository.existsByProductId(anyLong())).thenReturn(true);
    when(priceRepository.existsByBrandId(anyLong())).thenReturn(true);
    when(
        priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
        .thenReturn(list);

    PricesEntity price = priceAdapter.findPrice(1L, 1L, LocalDateTime.now());

    assertEquals(price, entity);
  }

  @Test
  public void testFindPrice_throwsProductNotFoundException() {
    when(priceRepository.existsByProductId(anyLong())).thenReturn(false);

    Assertions.assertThrows(ProductNotFoundException.class,
        () -> priceAdapter.findPrice(1L, 1L, LocalDateTime.now()));
  }

  @Test
  public void testFindPrice_throwsBrandNotFoundException() {
    when(priceRepository.existsByProductId(anyLong())).thenReturn(true);
    when(priceRepository.existsByBrandId(anyLong())).thenReturn(false);

    Assertions.assertThrows(BrandNotFoundException.class,
        () -> priceAdapter.findPrice(1L, 1L, LocalDateTime.now()));
  }

  @Test
  public void testFindPrice_throwsPricesNotFoundException() {
    when(priceRepository.existsByProductId(anyLong())).thenReturn(true);
    when(priceRepository.existsByBrandId(anyLong())).thenReturn(true);
    when(
        priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            anyLong(), anyLong(), any(LocalDateTime.class), any(LocalDateTime.class)))
        .thenReturn(List.of());

    Assertions.assertThrows(PricesNotFoundException.class,
        () -> priceAdapter.findPrice(1L, 1L, LocalDateTime.now()));
  }

}
