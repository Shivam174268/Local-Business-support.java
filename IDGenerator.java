package com.localbiz.util;

import java.util.UUID;

/**
 * Utility to generate unique IDs.
 */
public class IDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
