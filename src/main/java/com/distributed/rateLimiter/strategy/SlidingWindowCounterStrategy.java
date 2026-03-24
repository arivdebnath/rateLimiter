package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.models.RateLimitValue;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SlidingWindowCounterStrategy implements RateLimitingStrategy {

    private final Map<String, HashMap<Long, Integer>> userMap;

    public SlidingWindowCounterStrategy(Map<String, HashMap<Long, Integer>> userMap) {
        this.userMap = userMap;
    }

    @Override
    public boolean isAllowed(String key, RateLimitValue rateLimitValues) {
        Map<Long, Integer> windowMap = userMap.computeIfAbsent(key, k -> new HashMap<>());

        Long now = Instant.now().toEpochMilli();
        Long allowedWindowSize = Duration.of(rateLimitValues.timeLimit(), this.getTimeUnit(rateLimitValues.timeUnit())).toMillis();

        if(allowedWindowSize == 0){
            log.error("Window Size CAN NOT BE ZERO -- 0");
            throw new IllegalArgumentException();
        }

        Long windowStart = (now/allowedWindowSize) * allowedWindowSize;

        windowMap.entrySet().removeIf(e -> e.getKey() < windowStart - allowedWindowSize);

        Integer curWindowCount = windowMap.computeIfAbsent(windowStart, k -> 0);
        Integer prevWindowCount = windowMap.computeIfAbsent(windowStart-allowedWindowSize, k->0);
        double elapsed = (now - windowStart)/(double) allowedWindowSize;
        Integer prevCount = (int) Math.ceil(prevWindowCount * (1-elapsed));
        Integer curVal = curWindowCount + prevCount;


        if(curVal < rateLimitValues.allowedLimit()) {
            windowMap.merge(windowStart, 1, Integer::sum);
            return true;
        }
        return false;
    }
}
