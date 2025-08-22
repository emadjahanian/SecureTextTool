package com.fintech.encryptor;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Security;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

public class CryptoUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // گرفتن الگوریتم های موجود از provider BC
    public static Set<String> getAvailablePBEAlgorithms() {
        return Security.getProvider("BC").keySet().stream()
                .map(Object::toString)
                .filter(k -> k.startsWith("Cipher.") && k.contains("PBE"))
                .map(k -> k.replace("Cipher.", ""))
                .collect(Collectors.toSet());
    }

    public static String encrypt(String plainText, String password, String algorithm) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm, "BC");
        Cipher cipher = Cipher.getInstance(algorithm, "BC");
        byte[] salt = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int iterationCount = 1000;
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(keySpec), paramSpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText, String password, String algorithm) throws Exception {
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm, "BC");
        Cipher cipher = Cipher.getInstance(algorithm, "BC");
        byte[] salt = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        int iterationCount = 1000;
        PBEParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
        cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(keySpec), paramSpec);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decrypted);
    }
}
