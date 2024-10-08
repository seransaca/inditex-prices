package com.proof.inditex.infrastructure.adapters;

import com.proof.inditex.domain.entities.PricesEntity;
import com.proof.inditex.domain.exceptions.BrandNotFoundException;
import com.proof.inditex.domain.exceptions.PricesNotFoundException;
import com.proof.inditex.domain.exceptions.ProductNotFoundException;
import com.proof.inditex.domain.repositories.PricePort;
import com.proof.inditex.infrastructure.repositories.PriceRepository;
import java.time.LocalDateTime;
import java.util.Comparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceAdapter implements PricePort {

  private PriceRepository priceRepository;

  @Override
  public PricesEntity findPrice(Long productId, Long brandId, LocalDateTime date) {

    if (!priceRepository.existsByProductId(productId)) {
      throw new ProductNotFoundException(productId);
    }

    if (!priceRepository.existsByBrandId(brandId)) {
      throw new BrandNotFoundException(brandId);
    }

    return priceRepository.findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            productId, brandId, date, date)
        .stream()
        .sorted(Comparator.comparing(PricesEntity::getStartDate).reversed())
        .max(Comparator.comparing(PricesEntity::getPriority))
        .orElseThrow(() -> new PricesNotFoundException(productId, brandId, date));
  }

}
