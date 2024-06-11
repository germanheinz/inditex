package com.inditex.germanheinz.repository;

import com.inditex.germanheinz.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT u FROM Price u WHERE u.productId = :productId AND u.brand.id = :brandId")
    List<Price> findByBrandIdAndStartDateAndProductId(Integer productId, String brandId);

}
