package com.inditex.germanheinz.demo.kafka.producer.service.impl;


import com.inditex.germanheinz.kafka.order.avro.model.InditexAvroModel;
import com.inditex.germanheinz.demo.kafka.producer.service.KafkaProducer;
import jakarta.annotation.PreDestroy;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerImpl implements KafkaProducer<Long, InditexAvroModel> {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerImpl.class);

    private KafkaTemplate<Long, InditexAvroModel> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<Long, InditexAvroModel> template) {
        this.kafkaTemplate = template;
    }

    @Override
    public void send(String topicName, Long key, InditexAvroModel message) {
        LOG.info("Sending message='{}' to topic='{}'", message, topicName);
//        ListenableFuture<SendResult<Long, InditexAvroModel>> kafkaResultFuture =
//                kafkaTemplate.send(topicName, key, message);
//        addCallback(topicName, message, kafkaResultFuture);
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    private void addCallback(String topicName, InditexAvroModel message,
                             ListenableFuture<SendResult<Long, InditexAvroModel>> kafkaResultFuture) {
        kafkaResultFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }

            @Override
            public void onSuccess(SendResult<Long, InditexAvroModel> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                LOG.debug("Received new metadata. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            }
        });
    }
}
