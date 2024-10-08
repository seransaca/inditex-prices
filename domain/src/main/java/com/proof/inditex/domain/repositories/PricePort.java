package com.proof.inditex.domain.repositories;

import com.proof.inditex.domain.entities.PricesEntity;
import java.time.LocalDateTime;

public interface PricePort {

  PricesEntity findPrice(Long productId, Long brandId, LocalDateTime date);

}
