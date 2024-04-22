package com.inditex.germanheinz.demo.service.impl;

import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.mapper.PriceMapper;
import com.inditex.germanheinz.demo.model.PriceDto;
import com.inditex.germanheinz.demo.repository.PriceRepository;
import com.inditex.germanheinz.demo.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private PriceRepository priceRepository;
    private PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<PriceDto> getPrices() {
        List<Price> prices = priceRepository.findAll();
        logger.info("Get Prices from DB {}", prices);

        return priceMapper.pricesToPricesDto(prices);
    }
}
