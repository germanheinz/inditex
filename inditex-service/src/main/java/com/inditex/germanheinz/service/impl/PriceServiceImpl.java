package com.inditex.germanheinz.service.impl;

import com.inditex.germanheinz.InditexAvroModel;
import com.inditex.germanheinz.config.KafkaConfigData;
import com.inditex.germanheinz.entity.Price;
import com.inditex.germanheinz.mapper.PriceMapper;
import com.inditex.germanheinz.model.PriceDto;
import com.inditex.germanheinz.model.PriceRequestDto;
import com.inditex.germanheinz.repository.PriceRepository;
import com.inditex.germanheinz.service.KafkaProducer;
import com.inditex.germanheinz.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    public static final String MANGO = "mango";
    static Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    private final KafkaProducer<Long, InditexAvroModel> kafkaProducer;
    private final KafkaConfigData kafkaConfigData;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper, KafkaProducer<Long, InditexAvroModel> kafkaProducer, KafkaConfigData kafkaConfigData) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.kafkaProducer = kafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
    }

    @Override
    public List<PriceDto> getPrices(PriceRequestDto priceRequestDto) {
        logger.info("Get Prices init with Price Request {}", priceRequestDto.toString());
        List<Price> pricesJpa = priceRepository.findByBrandIdAndStartDateAndProductId(priceRequestDto.getProductId(), priceRequestDto.getBrandId());
        logger.info("Get Prices from DB {}", pricesJpa.get(0).toString());

        List<Price> prices = getPricesByDate(priceRequestDto, pricesJpa);
        logger.info("Prices {}", prices.toString());

        List<Price> filteredPrices = prices.stream()
                .filter(price -> MANGO.equalsIgnoreCase(price.getBrand().toString()))
                .toList();

        logger.info("Filtered Prices brand Mango, {}", filteredPrices);

        List<InditexAvroModel> inditexAvro = filteredPrices.stream()
                .map(price -> {
                    InditexAvroModel inditexAvroModel = new InditexAvroModel();
                    inditexAvroModel.setId(UUID.fromString(String.valueOf(price.getId()))); // Asegúrate de que getId() retorna un valor compatible con UUID
                    inditexAvroModel.setBrand(price.getBrand().toString());
                    inditexAvroModel.setPriority(price.getPriority().toString());
                    inditexAvroModel.setProductId(price.getProductId().toString());
                    return inditexAvroModel;
                })
                .toList();

        if (!inditexAvro.isEmpty()){
            inditexAvro.forEach(avroModel -> {
                logger.info("Prices for sending to Mango, {}", filteredPrices);
                kafkaProducer.send(kafkaConfigData.getTopicName(), Long.parseLong(avroModel.getId().toString()), avroModel);
            });
        }

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
