package com.localbusiness.util;

public class UserStore {
    public static boolean validate(String user, String pass) {
        return user.equals("admin") && pass.equals("admin");
    }
}
