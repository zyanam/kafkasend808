package com.cccts.kafkasend808.kafka.Configuration;

import com.zjts.beidou.agreement.Message808;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

/**
 * 自定义kafka的factory
 * 这个类里的 kafkaListenerContainerFactory 的名字是默认的，并且是必须的。SpringBoot2.1.9里，如果没用这个名称的bean会报错。
 */
@Configuration
public class CustomizeConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    //SpringBoot 为了不让SpringBoot自动配置出错。

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    //消费者工厂
    //key=String,value=Message808

    public Map<String, Object> msg808Configs() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    public ConsumerFactory<String, Message808> msg808ListenerContainerFactory() {

    }
}
