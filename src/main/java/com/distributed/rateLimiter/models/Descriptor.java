package com.distributed.rateLimiter.models;

import com.distributed.rateLimiter.enums.DurationUnit;

public class Descriptor {
    String key;
    String value;

    private class RateLimit {
        int limit;
        DurationUnit duration;
    }

    RateLimit rateLimit;
}
