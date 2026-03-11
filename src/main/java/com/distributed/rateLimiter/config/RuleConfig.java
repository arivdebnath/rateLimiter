package com.distributed.rateLimiter.config;

import com.distributed.rateLimiter.models.Rule;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "rules")
public class RuleConfig {
    List<Rule> rules;
}
