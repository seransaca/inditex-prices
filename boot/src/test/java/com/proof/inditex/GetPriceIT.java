package com.proof.inditex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.proof.inditex.api.dto.FindPriceRequestDTO;
import com.proof.inditex.api.dto.PriceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest
public class GetPriceIT extends BaseIT {

  private static final String URL_PATH_GET_PRICES = "/price";

  @BeforeEach
  public void init() {
    objectMapper.registerModule(new JavaTimeModule());
  }

  @Test
  @DisplayName("Get Prices Test 1")
  public void getPrice_Test1() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(URL_PATH_GET_PRICES)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(
                createFindPriceRequestDTO(35455L, 1L, "2020-06-14 10:00")
            )))
        .andExpect(status().is2xxSuccessful());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    PriceDTO priceDTO = objectMapper.readValue(contentAsString, PriceDTO.class);

    assertNotNull(priceDTO);
    assertEquals(35455L, priceDTO.getProductId());
    assertEquals(1L, priceDTO.getBrandId());
    assertEquals("35,50 EUR", priceDTO.getPrice());
    assertEquals(1, priceDTO.getPriceId().intValue());
    assertEquals("2020-06-14 00:00:00", priceDTO.getDateStart());
    assertEquals("2020-12-31 23:59:59", priceDTO.getDateEnd());

  }

  @Test
  @DisplayName("Get Prices Test 2")
  public void getPrice_Test2() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(URL_PATH_GET_PRICES)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(
                createFindPriceRequestDTO(35455L, 1L, "2020-06-14 16:00")
            )))
        .andExpect(status().is2xxSuccessful());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    PriceDTO priceDTO = objectMapper.readValue(contentAsString, PriceDTO.class);

    assertNotNull(priceDTO);
    assertEquals(35455L, priceDTO.getProductId());
    assertEquals(1L, priceDTO.getBrandId());
    assertEquals("25,45 EUR", priceDTO.getPrice());
    assertEquals(2, priceDTO.getPriceId().intValue());
    assertEquals("2020-06-14 15:00:00", priceDTO.getDateStart());
    assertEquals("2020-06-14 18:30:00", priceDTO.getDateEnd());

  }

  @Test
  @DisplayName("Get Prices Test 3")
  public void getPrice_Test3() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(URL_PATH_GET_PRICES)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(
                createFindPriceRequestDTO(35455L, 1L, "2020-06-14 21:00")
            )))
        .andExpect(status().is2xxSuccessful());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    PriceDTO priceDTO = objectMapper.readValue(contentAsString, PriceDTO.class);

    assertNotNull(priceDTO);
    assertEquals(35455L, priceDTO.getProductId());
    assertEquals(1L, priceDTO.getBrandId());
    assertEquals("35,50 EUR", priceDTO.getPrice());
    assertEquals(1, priceDTO.getPriceId().intValue());
    assertEquals("2020-06-14 00:00:00", priceDTO.getDateStart());
    assertEquals("2020-12-31 23:59:59", priceDTO.getDateEnd());

  }

  @Test
  @DisplayName("Get Prices Test 4")
  public void getPrice_Test4() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(URL_PATH_GET_PRICES)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(
                createFindPriceRequestDTO(35455L, 1L, "2020-06-15 10:00")
            )))
        .andExpect(status().is2xxSuccessful());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    PriceDTO priceDTO = objectMapper.readValue(contentAsString, PriceDTO.class);

    assertNotNull(priceDTO);
    assertEquals(35455L, priceDTO.getProductId());
    assertEquals(1L, priceDTO.getBrandId());
    assertEquals("30,50 EUR", priceDTO.getPrice());
    assertEquals(3, priceDTO.getPriceId().intValue());
    assertEquals("2020-06-15 00:00:00", priceDTO.getDateStart());
    assertEquals("2020-06-15 11:00:00", priceDTO.getDateEnd());

  }

  @Test
  @DisplayName("Get Prices Test 5")
  public void getPrice_Test5() throws Exception {
    ResultActions resultActions = mockMvc.perform(post(URL_PATH_GET_PRICES)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(
                createFindPriceRequestDTO(35455L, 1L, "2020-06-16 21:00")
            )))
        .andExpect(status().is2xxSuccessful());

    MvcResult result = resultActions.andReturn();
    String contentAsString = result.getResponse().getContentAsString();
    PriceDTO priceDTO = objectMapper.readValue(contentAsString, PriceDTO.class);

    assertNotNull(priceDTO);
    assertEquals(35455L, priceDTO.getProductId());
    assertEquals(1L, priceDTO.getBrandId());
    assertEquals("38,95 EUR", priceDTO.getPrice());
    assertEquals(4, priceDTO.getPriceId().intValue());
    assertEquals("2020-06-15 16:00:00", priceDTO.getDateStart());
    assertEquals("2020-12-31 23:59:59", priceDTO.getDateEnd());

  }

  private FindPriceRequestDTO createFindPriceRequestDTO(Long productId, Long brandId, String date) {
    return FindPriceRequestDTO.builder()
        .productId(productId)
        .brandId(brandId)
        .date(date)
        .build();
  }

}
