package com.inditex.germanheinz.demo.service.impl;

import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.mapper.PriceMapper;
import com.inditex.germanheinz.demo.model.PriceDto;
import com.inditex.germanheinz.demo.model.PriceRequestDto;
import com.inditex.germanheinz.demo.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @Mock
    private PriceMapper priceMapper;

    private PriceServiceImpl priceService;

    Logger logger = LoggerFactory.getLogger(PriceServiceImplTest.class);


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        priceService = new PriceServiceImpl(priceRepository, priceMapper);
    }

    @Test
    void testGetPrices() {
        PriceRequestDto priceRequestDto = new PriceRequestDto();
        priceRequestDto.setBrandId("1");
        priceRequestDto.setProductId(35455);
        priceRequestDto.setDate("2024-01-02 10:00:00");

        Price price1 = new Price();
        price1.setStartDate("2023-01-02 10:00:00");
        price1.setEndDate("2024-04-02 10:00:00");

        List<Price> pricesJpa = new ArrayList<>();
        pricesJpa.add(price1);

        PriceDto priceDto1 = new PriceDto();
        priceDto1.setId(1L);
        priceDto1.setStartDate("2023-01-02 10:00:00");
        priceDto1.setEndDate("2024-04-02 10:00:00");

        List<PriceDto> expectedPriceDtoList = new ArrayList<>();
        expectedPriceDtoList.add(priceDto1);

        when(priceRepository.findByBrandIdAndStartDateAndProductId(anyInt(), anyString())).thenReturn(pricesJpa);
        when(priceMapper.pricesToPricesDto(pricesJpa)).thenReturn(expectedPriceDtoList);

        List<PriceDto> actualPriceDtoList = priceService.getPrices(priceRequestDto);
        logger.info("actualPriceDtoList {}", actualPriceDtoList.toString());

        assertEquals(expectedPriceDtoList.size(), actualPriceDtoList.size());
    }
}