package com.cccts.kafkasend808.kafka.Configuration;

import lombok.Data;

import java.time.Duration;
import java.util.Map;

@Data
public  class Consumer {
    private Boolean enableAutoCommit;
    private Duration autoCommitInterval;
    private Class<?> keyDeserializer;
    private Class<?> valueDeserializer;
    private Map<String, String> properties;
}
