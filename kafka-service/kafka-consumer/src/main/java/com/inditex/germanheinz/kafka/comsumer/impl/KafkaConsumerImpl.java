package com.inditex.germanheinz.kafka.comsumer.impl;


import com.inditex.germanheinz.client.KafkaAdminClient;
import com.inditex.germanheinz.kafka.comsumer.KafkaConsumer;
import com.inditex.germanheinz.kafka.config.KafkaConfigData;
import com.inditex.germanheinz.kafka.config.KafkaConsumerConfigData;
import com.inditex.germanheinz.kafka.order.avro.model.InditexAvroModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class KafkaConsumerImpl implements KafkaConsumer<String, InditexAvroModel> {

    private  static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerImpl.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

//    private final AvroToElasticModelTransformer avroToElasticModelTransformer;

//    private final ElasticIndexClient<InditexAvroModel> elasticIndexClient;

    private final KafkaConsumerConfigData kafkaConsumerConfigData;


    public KafkaConsumerImpl(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry, KafkaAdminClient kafkaAdminClient, KafkaConfigData kafkaConfigData, KafkaConsumerConfigData kafkaConsumerConfigData) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
//        this.avroToElasticModelTransformer = avroToElasticModelTransformer;
        this.kafkaConsumerConfigData = kafkaConsumerConfigData;
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event){
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topic with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
//        Objects.requireNonNull(kafkaListenerEndpointRegistry
//                .getListenerContainer(kafkaConsumerConfigData.getConsumerGroupId())).start();
        Objects.requireNonNull(kafkaListenerEndpointRegistry
                .getListenerContainer("mango-topic-consumer")).start();
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.mango-consumer-group-id}",
                   topics = "${kafka-consumer-config.topic-name}",
    topicPartitions = {})
    public void receive(@Payload List<InditexAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets){

        LOG.info("**** CONSUMER **** Topic with name {}", kafkaConfigData.getTopicNamesToCreate().toArray());


        LOG.info("{} number of orders approval requests received with keys {}, partitions {} and offsets {}" +
                        ", sending for restaurant approval",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(inditexAvroModel -> {
            LOG.info("{} Message", inditexAvroModel);
        });
    }
}

