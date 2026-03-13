package com.distributed.rateLimiter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    LettuceConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    RedisTemplate<String, Integer> redisTemplate() {
        RedisTemplate<String, Integer> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.setKeySerializer(new  StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Integer.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
