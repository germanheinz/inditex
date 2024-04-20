package com.inditex.germanheinz.demo.service.impl;

import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.repository.PriceRepository;
import com.inditex.germanheinz.demo.service.PriceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }
    @Override
    public List<Price> getPrices() {
        return priceRepository.findAll();
    }
}
