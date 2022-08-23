package com.company.app.model.converter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
    private static DigestUtil instance;

    private DigestUtil() {
    }

    public static DigestUtil getInstance() {
        if (instance == null) {
            instance = new DigestUtil();
        }
        return instance;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            BigInteger bigInteger = new BigInteger(1, bytes);
            return bigInteger.toString(16).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
