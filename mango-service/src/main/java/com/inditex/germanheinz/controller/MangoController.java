package com.inditex.germanheinz.controller;

import com.inditex.germanheinz.api.MangoControllerApi;
import com.inditex.germanheinz.entity.Mango;
import com.inditex.germanheinz.model.MangoDto;
import com.inditex.germanheinz.service.MangoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/mango", produces = "application/vnd.api.v1+json")
public class MangoController implements MangoControllerApi {

    Logger logger = LoggerFactory.getLogger(MangoController.class);

    private final MangoService mangoService;

    public MangoController(MangoService mangoService) {
        this.mangoService = mangoService;
    }

    @GetMapping()
    public ResponseEntity<List<MangoDto>> getMangoData() {
        logger.info("API - Get Mango Data init");
        return ResponseEntity.ok(mangoService.getMangoData());
    }
}
