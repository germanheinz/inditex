package com.inditex.germanheinz.service.impl;


import com.microservices.demo.kafka.admin.client.KafkaAdminClient;
import com.microservices.demo.kafka.config.KafkaConfigData;
import com.microservices.demo.kafka.config.KafkaConsumerConfigData;
import com.microservices.kafka.model.TwitterAvroModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.inditex.germanheinz.service.KafkaConsumer;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class MangoKafkaConsumerImpl implements KafkaConsumer<Long, TwitterAvroModel> {

    private  static final Logger LOG = LoggerFactory.getLogger(MangoKafkaConsumerImpl.class);

    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final KafkaAdminClient kafkaAdminClient;

    private final KafkaConfigData kafkaConfigData;

    private final KafkaConsumerConfigData kafkaConsumerConfigData;


    public MangoKafkaConsumerImpl(KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry, KafkaAdminClient kafkaAdminClient, KafkaConfigData kafkaConfigData, KafkaConsumerConfigData kafkaConsumerConfigData) {
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaAdminClient = kafkaAdminClient;
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaConsumerConfigData = kafkaConsumerConfigData;
    }

    @EventListener
    public void onAppStarted(ApplicationStartedEvent event){
        kafkaAdminClient.checkTopicsCreated();
        LOG.info("Topic with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        Objects.requireNonNull(kafkaListenerEndpointRegistry
                .getListenerContainer(kafkaConsumerConfigData.getConsumerGroupId())).start();
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.consumer-group-id}", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<TwitterAvroModel> messages, List<Integer> keys, List<Integer> partitions, List<Long> offsets) {

        messages.forEach(message -> {
            try {
                LOG.info("LALAAAA LOMM!!!!!!!!!!!! Message received with key {}, partition {} and offset {}, sending it to elastic: Thread id {}", message.getId(), message.getText(), message.getUserId(), Thread.currentThread().getId());

                // TODO SAVE INTO MANGO DB
            } catch (DataAccessException e) {
                SQLException sqlException = (SQLException) e.getRootCause();
                if (sqlException != null && sqlException.getSQLState() != null) {
                    LOG.error("Caught unique constraint exception with sql state: {} " +
                                    "in RestaurantApprovalRequestKafkaListener for order id: {}",
                            sqlException.getSQLState(), message.getText());
                } else {
                    throw new RuntimeException("Throwing DataAccessException in" +
                            " RestaurantApprovalRequestKafkaListener: " + e.getMessage(), e);
                }
            } catch (Exception e) {
                LOG.error("No response from Kafka id: {}, and text: {}",
                        message.getId(),
                        message.getText());
            }
        });
    }
}
