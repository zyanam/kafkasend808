package com.cccts.kafkasend808.kafka.Configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

public class InboundMsgLocationConfig {

    @Autowired
    private KafkaProperties properties;

    @Bean
    public Map<String, Object> inboundMsgLocationConsumerConfigs() {
        Map<String, Object> props = new HashMap<>(properties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> inboundMsgLocationConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(inboundMsgLocationConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> inboundMsgLocationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(inboundMsgLocationConsumerFactory());
        return factory;
    }
}
