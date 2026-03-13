package com.distributed.rateLimiter.models;

public record Descriptor(
        String key,
        String value,
        RateLimitValue rateLimit
) {}
