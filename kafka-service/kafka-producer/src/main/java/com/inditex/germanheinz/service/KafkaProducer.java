package com.inditex.germanheinz.service;

import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends SpecificRecordBase> {
    void send(String topicName, String key, V message);
}
