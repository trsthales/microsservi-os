package com.example.ecommerce.common.events;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

public class SimpleDomainEvent implements DomainEvent {
    private final String eventType;
    private final Map<String, Object> payload;
    private final Instant occurredAt;

    public SimpleDomainEvent(String eventType, Map<String, Object> payload) {
        this.eventType = Objects.requireNonNull(eventType, "eventType");
        this.payload = Objects.requireNonNull(payload, "payload");
        this.occurredAt = Instant.now();
    }

    @Override
    public String eventType() {
        return eventType;
    }

    public Map<String, Object> payload() { return payload; }
    public Instant occurredAt() { return occurredAt; }
}

