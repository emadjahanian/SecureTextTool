package com.fintech.encryptor;

import java.security.Security;
import java.util.Set;
import java.util.stream.Collectors;

public enum AlgorithmType {
    PBEWITHMD5ANDDES("PBEWithMD5AndDES"),
    PBEWITHMD5ANDTRIPLEDES("PBEWithMD5AndTripleDES"),
    PBEWITHSHA1ANDDESEDE("PBEWithSHA1AndDESede"),
    PBEWITHSHA1ANDRC2_40("PBEWithSHA1AndRC2_40"),
    PBEWITHSHA1ANDRC2_64("PBEWithSHA1AndRC2_64"),
    PBEWITHSHA1ANDRC4_128("PBEWithSHA1AndRC4_128"),
    AES256_SHA256("AES256_SHA256"); // نمونه، بعدا می‌تونیم الگوریتم‌های BC واقعی اضافه کنیم

    private final String algorithm;

    AlgorithmType(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public static AlgorithmType fromNumber(int number) {
        AlgorithmType[] values = AlgorithmType.values();
        if (number < 1 || number > values.length) {
            throw new IllegalArgumentException("Invalid algorithm number");
        }
        return values[number - 1];
    }

    public static void printAvailableAlgorithms() {
        AlgorithmType[] values = AlgorithmType.values();
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%d. %s%n", i + 1, values[i].getAlgorithm());
        }
    }

    // برای dynamic گرفتن الگوریتم‌ها از BouncyCastle
    public static Set<String> getDynamicAlgorithms() {
        return Security.getProvider("BC").keySet().stream()
                .map(Object::toString)
                .filter(k -> k.startsWith("Cipher.") && k.contains("PBE"))
                .map(k -> k.replace("Cipher.", ""))
                .collect(Collectors.toSet());
    }
}

