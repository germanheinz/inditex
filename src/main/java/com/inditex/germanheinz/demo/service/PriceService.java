package com.inditex.germanheinz.demo.service;

import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.model.PriceDto;

import java.util.List;

public interface PriceService {
    List<PriceDto> getPrices();
}
