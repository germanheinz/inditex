package com.inditex.germanheinz.demo.repository;

import com.inditex.germanheinz.demo.entity.Price;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Id> {}
