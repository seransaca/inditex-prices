package com.proof.inditex.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PRICES")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PricesEntity {

  @Id
  private Long id;
  @Column(name = "product_id")
  private Long productId;
  @Column(name = "brand_id")
  private Long brandId;
  @Column(name = "start_date")
  private LocalDateTime startDate;
  @Column(name = "end_date")
  private LocalDateTime endDate;
  @Column(name = "price_list")
  private Integer priceList;
  @Column(name = "priority")
  private Integer priority;
  @Column(name = "price")
  private Double price;
  @Column(name = "curr")
  private String curr;

}
