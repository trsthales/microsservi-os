package com.example.ecommerce.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdGeneratorTest {

    @Test
    void randomId_notNullAndDifferent() {
        String a = IdGenerator.randomId();
        String b = IdGenerator.randomId();
        assertNotNull(a);
        assertNotNull(b);
        assertNotEquals(a, b);
    }
}

