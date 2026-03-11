package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.enums.DurationUnit;

public class FixedWindowCounter implements RateLimitingStrategy{
    @Override
    public boolean isAllowed(String key, int allowedLimit, DurationUnit durationUnit) {

    }
}
