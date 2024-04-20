package com.inditex.germanheinz.demo.controller;


import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/price", produces = "application/vnd.api.v1+json")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping()
    public ResponseEntity<List<Price>> getOrderByTrackingId() {
        log.info("Returning order status with tracking id: {}");
        return ResponseEntity.ok(priceService.getPrices());
    }

}
