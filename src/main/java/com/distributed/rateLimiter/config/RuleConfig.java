package com.distributed.rateLimiter.config;

import com.distributed.rateLimiter.models.Rule;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "rules")
public class RuleConfig {
    List<Rule> rules;
}
