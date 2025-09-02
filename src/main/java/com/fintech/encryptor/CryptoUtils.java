package com.fintech.encryptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.NoIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;

import java.security.Security;

public class CryptoUtils {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String plainText, String password, String algorithm) {
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(password);
            encryptor.setAlgorithm(algorithm);
            encryptor.setSaltGenerator(new RandomSaltGenerator());
            encryptor.setIvGenerator(new NoIvGenerator());
            encryptor.setKeyObtentionIterations(1000);
            return encryptor.encrypt(plainText);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed: " + e.getMessage(), e);
        }
    }

    public static String decrypt(String cipherText, String password, String algorithm) {
        try {
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            encryptor.setPassword(password);
            encryptor.setAlgorithm(algorithm);
            encryptor.setSaltGenerator(new RandomSaltGenerator());
            encryptor.setIvGenerator(new NoIvGenerator());
            encryptor.setKeyObtentionIterations(1000);
            return encryptor.decrypt(cipherText);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed: " + e.getMessage(), e);
        }
    }
}
