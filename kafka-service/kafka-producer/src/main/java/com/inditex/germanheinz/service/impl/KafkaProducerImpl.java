package com.inditex.germanheinz.service.impl;


import com.inditex.germanheinz.InditexAvroModel;
import com.inditex.germanheinz.config.KafkaConfigData;
import com.inditex.germanheinz.service.KafkaProducer;
import jakarta.annotation.PreDestroy;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerImpl implements KafkaProducer<String, InditexAvroModel> {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerImpl.class);

    private final KafkaConfigData kafkaConfigData;

    private final KafkaTemplate<String, InditexAvroModel> kafkaTemplate;

    public KafkaProducerImpl(KafkaConfigData kafkaConfigData, KafkaTemplate<String, InditexAvroModel> kafkaTemplate) {
        this.kafkaConfigData = kafkaConfigData;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, String key, InditexAvroModel message) {
        LOG.info("Sending message='{}' to topic='{}'", message, topicName);
        CompletableFuture<SendResult<String, InditexAvroModel>> kafkaResponse = kafkaTemplate.send(topicName, message.getId().toString(), message);
        getKafkaCallback(kafkaConfigData.getTopicName(), InditexAvroModel.class, "1", "InditexAvroModel");
        LOG.info("Producer Response message='{}' to topic='{}'", kafkaResponse, topicName);
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
    public <T, U> ListenableFutureCallback<SendResult<String, T>> getKafkaCallback(String responseTopicName, T avroModel, String id, String avroModelName) {
        return new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOG.error("Error while sending {} with message: {} to topic {}",
                        avroModelName, avroModel.toString(), responseTopicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                LOG.info("Received successful response from Kafka" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());
            }
        };
    }
}
