package com.proof.inditex.infrastructure.repositories;

import com.proof.inditex.domain.entities.PricesEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PricesEntity, Long> {

  boolean existsByProductId(Long productId);

  boolean existsByBrandId(Long brandId);

  List<PricesEntity> findByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
      Long productId, Long brandId, LocalDateTime searchDateBefore, LocalDateTime searchDateAfter);
}
