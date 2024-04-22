package com.inditex.germanheinz.demo.repository;

import com.inditex.germanheinz.demo.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {}
