package com.inditex.germanheinz.demo.repository;

import com.inditex.germanheinz.demo.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {}
