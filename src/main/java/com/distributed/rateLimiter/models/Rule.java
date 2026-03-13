package com.distributed.rateLimiter.models;

import java.util.List;

public record Rule(String id, Integer limit, List<Descriptor> descriptors) {}