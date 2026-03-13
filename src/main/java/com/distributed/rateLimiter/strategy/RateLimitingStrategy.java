package com.distributed.rateLimiter.strategy;

import com.distributed.rateLimiter.enums.DurationUnit;
import com.distributed.rateLimiter.models.RateLimitValue;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.util.RateLimiter;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public interface RateLimitingStrategy{
    public boolean isAllowed(String key, RateLimitValue rateLimitValues);

    default TemporalUnit getTimeUnit(DurationUnit durationUnit) {
        TemporalUnit temporalUnit = null;
        switch (durationUnit) {
            case SECONDS:
                temporalUnit = ChronoUnit.SECONDS;
                break;
            case MINUTES:
                temporalUnit = ChronoUnit.MINUTES;
                break;
            case HOURS:
                temporalUnit = ChronoUnit.HOURS;
                break;
            case DAYS:
                temporalUnit = ChronoUnit.DAYS;
                break;
            case null, default:
                break;
        }
        return temporalUnit;
    }
}
