package com.fintech.encryptor;

public enum PBEAlgorithm {

    PBEWITHMD5ANDDES("PBEWITHMD5ANDDES"),
    PBEWITHMD5ANDTRIPLEDES("PBEWITHMD5ANDTRIPLEDES"),
    PBEWITHSHA1ANDDES("PBEWITHSHA1ANDDES"),
    PBEWITHSHA1ANDDESEDE("PBEWITHSHA1ANDDESEDE"),
    PBEWITHSHA256AND128BITAES_CBC_BC("PBEWITHSHA256AND128BITAES-CBC-BC"),
    PBEWITHSHA256AND192BITAES_CBC_BC("PBEWITHSHA256AND192BITAES-CBC-BC"),
    PBEWITHSHA256AND256BITAES_CBC_BC("PBEWITHSHA256AND256BITAES-CBC-BC");

    private final String algorithm;

    PBEAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    @Override
    public String toString() {
        return algorithm;
    }
}
