package com.inditex.germanheinz.demo.controller;

import com.inditex.germanheinz.demo.model.PriceDto;
import com.inditex.germanheinz.demo.model.PriceRequestDto;
import com.inditex.germanheinz.demo.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPriceRequestAt10AM() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T10:00:00");
        testPriceRequest(dateTime, "Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)", HttpStatus.OK);
    }

    @Test
    void testPriceRequestAt4PM() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T16:00:00");
        testPriceRequest(dateTime, "Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)", HttpStatus.OK);
    }

    @Test
    void testPriceRequestAt9PM() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-14T21:00:00");
        testPriceRequest(dateTime, "Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)", HttpStatus.OK);
    }

    @Test
    void testPriceRequestNextDay() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-06-15T10:00:00");
        testPriceRequest(dateTime, "Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)", HttpStatus.OK);
    }

    private void testPriceRequest(LocalDateTime dateTime, String logMessage, HttpStatus expectedStatus) {
        PriceRequestDto requestDto = new PriceRequestDto();
        requestDto.setBrandId("1");
        requestDto.setProductId(35455);
        requestDto.setDate(dateTime.toString());

        PriceDto priceDto = new PriceDto();
        List<PriceDto> priceDtoList = Arrays.asList(priceDto);
        when(priceService.getPrices(requestDto)).thenReturn(priceDtoList);

        ResponseEntity<List<PriceDto>> responseEntity = priceController.getPrices(requestDto);

        assertEquals(expectedStatus, responseEntity.getStatusCode());

        System.out.println(logMessage);
    }
}