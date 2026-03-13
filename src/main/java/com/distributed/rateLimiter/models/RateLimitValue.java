package com.distributed.rateLimiter.models;

import com.distributed.rateLimiter.enums.DurationUnit;

public record RateLimitValue(int allowedLimit, DurationUnit timeUnit, int timeLimit) {}