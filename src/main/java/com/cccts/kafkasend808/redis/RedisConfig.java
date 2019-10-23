package com.cccts.kafkasend808.redis;

import com.beidou.beidou_redis.factory.BeidouRedisSerializerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@ComponentScan(basePackages = "com.beidou.beidou_redis")
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private BeidouRedisSerializerFactory beidouRedisSerializerFactory;

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
       RedisTemplate<String,Object> template = new RedisTemplate<>();

       template.setConnectionFactory(factory);
        // key采用String的序列化方式

        template.setKeySerializer(beidouRedisSerializerFactory.getKeySerializerFactory());
        // hash的key也采用String的序列化方式

        template.setHashKeySerializer(beidouRedisSerializerFactory.getKeySerializerFactory());

        // value序列化方式采用fastJson

        template.setValueSerializer(beidouRedisSerializerFactory.getValueSerializerFactory());

        // hash的value序列化方式采用fastJson

        template.setHashValueSerializer(beidouRedisSerializerFactory.getValueSerializerFactory());

        template.afterPropertiesSet();

       return template;
    }
}
