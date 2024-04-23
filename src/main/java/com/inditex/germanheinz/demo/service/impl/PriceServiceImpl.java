package com.inditex.germanheinz.demo.service.impl;

import com.inditex.germanheinz.demo.entity.Price;
import com.inditex.germanheinz.demo.mapper.PriceMapper;
import com.inditex.germanheinz.demo.model.PriceDto;
import com.inditex.germanheinz.demo.model.PriceRequestDto;
import com.inditex.germanheinz.demo.repository.PriceRepository;
import com.inditex.germanheinz.demo.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    static Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private PriceRepository priceRepository;
    private PriceMapper priceMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<PriceDto> getPrices(PriceRequestDto priceRequestDto) {
        logger.info("Get Prices init with Price Request {}", priceRequestDto.toString());
        List<Price> pricesJpa = priceRepository.findByBrandIdAndStartDateAndProductId(priceRequestDto.getProductId(), priceRequestDto.getBrandId());
        logger.info("Get Prices from DB {}", pricesJpa.get(0).toString());

        List<Price> prices = getPricesByDate(priceRequestDto, pricesJpa);
        logger.info("Prices {}", prices.toString());

        return priceMapper.pricesToPricesDto(prices);
    }

    private static List<Price> getPricesByDate(PriceRequestDto priceRequestDto, List<Price> prices) {
        return prices.stream()
                .filter(price -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    LocalDateTime date = LocalDateTime.parse(priceRequestDto.getDate(), formatter);
                    LocalDateTime endDate = LocalDateTime.parse(price.getEndDate(), formatter);
                    LocalDateTime startDate = LocalDateTime.parse(price.getStartDate(), formatter);

                    logger.info("date {}, startDate {}, endDate {}", date, startDate, endDate);

                    return startDate.isBefore(date) && endDate.isAfter(date);

                })
                .collect(Collectors.toList());
    }
}
