package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.models.RateLimitValue;

import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowLog implements RateLimitingStrategy{
    ConcurrentHashMap<String, TreeSet<Long>> rateMap;

    public SlidingWindowLog() {
        rateMap = new ConcurrentHashMap<>();
    }

    public boolean isAllowed(String key, RateLimitValue rateLimitValue) {  //key - user:context(path/domain/descriptor)
        TreeSet<Long> set = rateMap.computeIfAbsent(key, k -> new TreeSet<>());

        synchronized (set) {
            Long now = Instant.now().toEpochMilli();

            TreeSet<Long> updatedSet = (TreeSet<Long>) set.tailSet(now - Duration.of(rateLimitValue.timeLimit(), getTimeUnit(rateLimitValue.timeUnit())).getSeconds() * 1000L);

            if (updatedSet.size() >= rateLimitValue.allowedLimit()) {
                return false;
            }

            set.add(now);
            rateMap.put(key, updatedSet);
        }

        return true;
    }

}
