package com.inditex.germanheinz.controller;


import com.inditex.germanheinz.api.PriceControllerApi;
import com.inditex.germanheinz.model.PriceDto;
import com.inditex.germanheinz.model.PriceRequestDto;
import com.inditex.germanheinz.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/price", produces = "application/vnd.api.v1+json")
public class PriceController implements PriceControllerApi {

    Logger logger = LoggerFactory.getLogger(PriceController.class);

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<List<PriceDto>> getPrices(PriceRequestDto priceRequestDto) {
        logger.info("API - Get Prices init with Price Request {}", priceRequestDto.toString());
        return ResponseEntity.ok(priceService.getPrices(priceRequestDto));
    }
}
