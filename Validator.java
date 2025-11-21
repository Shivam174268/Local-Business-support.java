package com.localbiz.util;

import java.util.regex.Pattern;

/**
 * Utility class to validate inputs.
 */
public class Validator {
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\d{10}");

    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}
