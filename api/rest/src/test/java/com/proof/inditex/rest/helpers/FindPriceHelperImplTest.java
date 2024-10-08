package com.proof.inditex.rest.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import com.proof.inditex.domain.services.FindPriceService;
import com.proof.inditex.domain.shared.Price;
import com.proof.inditex.rest.BaseTest;
import com.proof.inditex.rest.helpers.impl.FindPriceHelperImpl;
import com.proof.inditex.rest.mappers.RestPriceMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class FindPriceHelperImplTest extends BaseTest {

  @Mock
  private RestPriceMapper restPriceMapper;

  @Mock
  private FindPriceService findPriceService;

  @InjectMocks
  private FindPriceHelperImpl findPriceHelperImpl;

  @Test
  public void testFindPrices_returnsOk() {
    FindPriceRequestDTO findProductRequest = createFindPriceRequestDTO(); // Create a sample request
    Price price = createPrice(null, null); // Create a sample product
    Price priceReturned = createPrice(LocalDateTime.now().toString(),
        LocalDateTime.now().plusHours(2).toString()); // Create a sample product returned
    PriceDTO priceDTO = createPriceDTO(priceReturned.getId(), priceReturned.getBrandId(),
        priceReturned.getPrice(), priceReturned.getPriceList(),
        priceReturned.getStartDate().toString(),
        priceReturned.getEndDate().toString()); // Create a sample product returned

    when(restPriceMapper.mapDTOToPrice(findProductRequest)).thenReturn(price);
    when(findPriceService.findPrice(price)).thenReturn(priceReturned);
    when(restPriceMapper.mapPriceToPriceDTO(priceReturned)).thenReturn(priceDTO);

    // Act
    ResponseEntity<PriceDTO> response = findPriceHelperImpl.findPrice(findProductRequest);

    // Assert
    assertEquals(response.getBody(), priceDTO);
  }
}

