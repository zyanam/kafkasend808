package com.cccts.kafkasend808.kafka.Configuration;

import com.cccts.kafkasend808.kafka.common.Msg808Deserializer;
import com.cccts.kafkasend808.kafka.common.Msg808Serializer;
import com.zjts.beidou.agreement.Message808;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义kafka的factory
 * 这个类里的 kafkaListenerContainerFactory 的名字是默认的，并且是必须的。SpringBoot2.1.9里，如果没用这个名称的bean会报错。
 */
@Configuration
public class CustomizeConfig {

    @Autowired
    private KafkaProperties kafkaProperties;


    //生产者工厂
    //key=String,value=Message808

    @Bean
    public Map<String, Object> msg808ProducerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Msg808Serializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Message808> msg808ProducerFactory() {
        return new DefaultKafkaProducerFactory<>(msg808ProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Message808> msg808Template() {
        return new KafkaTemplate<>(msg808ProducerFactory());
    }

    //生产者工厂
    //key=String,value=byte[]
    @Bean
    public Map<String, Object> byteArrayProducerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildProducerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, byte[]> byteArrayProducerFactory() {
        return new DefaultKafkaProducerFactory<>(byteArrayProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, byte[]> bytesTemplate() {
        return new KafkaTemplate<>(byteArrayProducerFactory());
    }

    //生产者工厂
    //key=Byte,value=String
    @Bean
    public Map<String, Object> byteKeyProducerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, BytesSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, BytesSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Byte, String> byteKeyProducerFactory() {
        return new DefaultKafkaProducerFactory<>(byteKeyProducerConfigs());
    }

    @Bean
    public KafkaTemplate<Byte, String> byteKeyTemplate() {
        return new KafkaTemplate<>(byteKeyProducerFactory());
    }


    //默认消费者工厂
    //key=String,Value=String。
    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    //消费者工厂
    //key=String,value=Message808

    @Bean
    public Map<String, Object> msg808ConsumerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Msg808Deserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Message808> msg808ConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(msg808ConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message808> msg808ListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Message808> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(msg808ConsumerFactory());
        return factory;
    }

    //消费者工厂
    //key=Byte,value=String

    @Bean
    public Map<String, Object> stringConsumerConfigs() {
        Map<String, Object> props = new HashMap<>(kafkaProperties.buildConsumerProperties());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, BytesDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<Byte, String> stringConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(stringConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Byte, String> stringListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Byte, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stringConsumerFactory());
        return factory;
    }

    //key=String,value=byte[]
    @Bean
    public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(stringConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, byte[]> byteArrayListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(byteArrayConsumerFactory());
        return factory;
    }
}
