package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.models.RateLimitValue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Component
public class FixedWindowCounter implements RateLimitingStrategy{

    private final RedisTemplate<String, Integer> redisTemplate;
    private final ValueOperations<String, Integer> valOps;

    public FixedWindowCounter(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valOps = redisTemplate.opsForValue();
    }

    @Override
    public boolean isAllowed(String key, RateLimitValue rateLimitValue) {
        long count = valOps.increment(key);
        if(count==1) {
            redisTemplate.expire(key, Duration.of(rateLimitValue.timeLimit(), ChronoUnit.SECONDS));
        }

        Integer currentCount = valOps.get(key);
        return currentCount != null && currentCount < rateLimitValue.timeLimit();
    }


}
