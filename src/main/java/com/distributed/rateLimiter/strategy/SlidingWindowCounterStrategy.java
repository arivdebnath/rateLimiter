package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.enums.DurationUnit;
import com.distributed.rateLimiter.models.RateLimitValue;

public class SlidingWindowCounterStrategy implements RateLimitingStrategy {
    @Override
    public boolean isAllowed(String key, RateLimitValue rateLimitValues) {
        return false;
    }
}
