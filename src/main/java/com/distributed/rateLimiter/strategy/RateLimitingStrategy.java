package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.enums.DurationUnit;

public interface RateLimitingStrategy{
    public boolean isAllowed(String key, int allowedLimit, DurationUnit durationUnit);
}
