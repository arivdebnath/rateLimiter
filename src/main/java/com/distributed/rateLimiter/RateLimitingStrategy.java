package com.distributed.rateLimiter;

public interface RateLimitingStrategy{

    public boolean isRequestAllowed(String key, int limit, int duration);
}
