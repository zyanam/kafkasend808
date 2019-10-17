package com.cccts.kafkasend808.kafka.Configuration;

import lombok.Data;
import org.springframework.util.unit.DataSize;

import java.util.Map;

@Data
public class Producer {
    private Integer retries;
    private DataSize batchSize;
    private DataSize bufferMemory;
    private Class<?> keyDeserializer;
    private Class<?> valueDeserializer;
    private Map<String, String> properties;
}
