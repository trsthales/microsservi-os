package com.example.ecommerce.common.events;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDomainEventTest {

    @Test
    void eventFields() {
        Map<String, Object> payload = Map.of("k", (Object) "v");
        SimpleDomainEvent e = new SimpleDomainEvent("T", payload);
        assertEquals("T", e.eventType());
        assertEquals(payload, e.payload());
        assertNotNull(e.occurredAt());
    }
}
