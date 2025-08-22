package com.fintech.encryptor;

import java.util.Map;

public class UserStore {

    private static final Map<String, String> USERS = Map.of(
            "admin", "admin123",
            "user1", "password1"
    );

    public static boolean validateUser(String username, String password) {
        return USERS.containsKey(username) && USERS.get(username).equals(password);
    }
}
