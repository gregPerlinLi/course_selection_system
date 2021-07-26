package com.gregperlinli.utils;

import org.junit.jupiter.api.Test;

public class MD5EncryptTest {
    @Test
    public void testStringMD5() {
        String plaintext = "abcdef123456";
        String ciphertext = MD5Encrypt.stringMD5(plaintext);
        System.out.println(ciphertext);
    }
}
