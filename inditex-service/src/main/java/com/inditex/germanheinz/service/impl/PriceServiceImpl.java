package com.inditex.germanheinz.service.impl;

import com.inditex.germanheinz.InditexAvroModel;
import com.inditex.germanheinz.KafkaMessageHelper;
import com.inditex.germanheinz.config.InditexServiceConfigData;
import com.inditex.germanheinz.entity.Price;
import com.inditex.germanheinz.kafka.config.KafkaConfigData;
import com.inditex.germanheinz.mapper.PriceMapper;
import com.inditex.germanheinz.model.PriceDto;
import com.inditex.germanheinz.model.PriceRequestDto;
import com.inditex.germanheinz.repository.PriceRepository;
import com.inditex.germanheinz.service.KafkaProducer;
import com.inditex.germanheinz.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    public static final String ZARA = "ZARA";
    static Logger logger = LoggerFactory.getLogger(PriceServiceImpl.class);

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    private final InditexServiceConfigData inditexServiceConfigData;

    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<String, InditexAvroModel> kafkaProducer;

    private final KafkaMessageHelper kafkaMessageHelper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceMapper priceMapper, InditexServiceConfigData inditexServiceConfigData, KafkaProducer<String, InditexAvroModel> kafkaProducer, KafkaConfigData kafkaConfigData, KafkaMessageHelper kafkaMessageHelper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.inditexServiceConfigData = inditexServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public List<PriceDto> getPrices(PriceRequestDto priceRequestDto) {
        logger.info("Get Prices init with Price Request {}", priceRequestDto.toString());
        List<Price> pricesJpa = priceRepository.findByBrandIdAndStartDateAndProductId(priceRequestDto.getProductId(), priceRequestDto.getBrandId());
        logger.info("Get Prices from DB {}", pricesJpa.get(0).toString());

        List<Price> prices = getPricesByDate(priceRequestDto, pricesJpa);
        logger.info("Prices {}", prices.toString());

        List<Price> filteredPrices = prices.stream()
                .filter(price -> ZARA.equalsIgnoreCase(price.getBrand().getName()))
                .toList();

        logger.info("Filtered Prices brand Mango, {}", filteredPrices);
        InditexAvroModel inditexAvroModel = new InditexAvroModel();
        List<InditexAvroModel> inditexAvro = filteredPrices.stream()
                .map(price -> {

                    inditexAvroModel.setId(UUID.randomUUID()); // Asegúrate de que getId() retorna un valor compatible con UUID
                    inditexAvroModel.setBrand(price.getBrand().toString());
                    inditexAvroModel.setPriority(price.getPriority().toString());
                    inditexAvroModel.setProductId(price.getProductId().toString());
                    return inditexAvroModel;
                })
                .toList();

        if (!inditexAvro.isEmpty()){
            inditexAvro.forEach(avroModel -> {
                logger.info("Prices for sending to Mango, {}", filteredPrices);

                kafkaProducer.send(
                        inditexServiceConfigData.getInditexRequestTopicName(),
                        avroModel.getId().toString(),
                        avroModel,
                        kafkaMessageHelper.getKafkaCallback(
                                inditexServiceConfigData.getInditexRequestTopicName(),
                                avroModel,
                                (o, outboxStatus) -> {},
                                avroModel.getId().toString(),
                                "InditexAvroModel")
                );

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
