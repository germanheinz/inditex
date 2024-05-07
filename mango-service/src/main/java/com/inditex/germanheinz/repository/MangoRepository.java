package com.inditex.germanheinz.repository;

import com.inditex.germanheinz.entity.Mango;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MangoRepository extends JpaRepository<Mango, Long> {}
