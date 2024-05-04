package com.inditex.germanheinz.demo.service;

import com.inditex.germanheinz.demo.model.PriceDto;
import com.inditex.germanheinz.demo.model.PriceRequestDto;

import java.util.List;

public interface PriceService {
    List<PriceDto> getPrices(PriceRequestDto priceRequestDto);
}
