package com.inditex.germanheinz.service.impl;


import com.inditex.germanheinz.DemoApplication;
import com.inditex.germanheinz.kafka.comsumer.KafkaConsumer;
import com.inditex.germanheinz.kafka.order.avro.model.InditexAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InditexKafkaListener implements KafkaConsumer<InditexAvroModel> {
    private static final Logger LOG = LoggerFactory.getLogger(DemoApplication.class);
    @Override
    @KafkaListener(id = "${kafka-consumer-config.mango-consumer-group-id}",
            topics = "${kafka-consumer-config.topic-name}",
            topicPartitions = {})
    public void receive(@Payload List<InditexAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        LOG.info("{} number of customer create messages received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(customerAvroModel -> {
            LOG.info("MESSAGGEEE LISTENNER {}", customerAvroModel.getId());
        });

        }
}
