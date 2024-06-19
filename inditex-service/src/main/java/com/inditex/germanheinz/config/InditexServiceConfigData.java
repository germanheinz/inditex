package com.inditex.germanheinz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "inditex-kafka-service")
public class InditexServiceConfigData {
    private String inditexRequestTopicName;
    private String inditexResponseTopicName;
}
