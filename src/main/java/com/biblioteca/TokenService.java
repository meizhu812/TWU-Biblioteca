package com.biblioteca;

import com.biblioteca.model.User;

import java.util.HashMap;
import java.util.UUID;

public class TokenService {

    private static TokenService tokenService;
    private HashMap<String, User> tokenUserMap;

    public static TokenService getInstance() {
        if (tokenService == null) {
            tokenService = new TokenService();
        }
        return tokenService;
    }

    private TokenService() {
        tokenUserMap = new HashMap<>();
    }

    public String generateTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        tokenUserMap.put(token, user);
        return token;
    }

    public User getUserFromToken(String token) throws Exception {
        if (tokenUserMap.containsKey(token)) {
            return tokenUserMap.get(token);
        }
        throw new Exception("Invalid Token");
    }
}