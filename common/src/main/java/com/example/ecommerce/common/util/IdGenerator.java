package com.example.ecommerce.common.util;

import java.util.UUID;

public final class IdGenerator {
    private IdGenerator() {}
    public static String randomId() { return UUID.randomUUID().toString(); }
}

