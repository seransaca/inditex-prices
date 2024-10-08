package com.proof.inditex.domain.shared;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Price {

  private Long id;
  private Long brandId;
  private LocalDateTime date;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Long priceList;
  private String price;
  ;

}
