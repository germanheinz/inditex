package com.inditex.germanheinz.service;

import com.inditex.germanheinz.model.PriceDto;
import com.inditex.germanheinz.model.PriceRequestDto;

import java.util.List;

public interface PriceService {
    List<PriceDto> getPrices(PriceRequestDto priceRequestDto);
}
