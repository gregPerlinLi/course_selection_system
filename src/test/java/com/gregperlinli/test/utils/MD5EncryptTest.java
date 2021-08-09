package com.gregperlinli.test.utils;

import com.gregperlinli.utils.MD5Encrypt;
import org.junit.jupiter.api.Test;

public class MD5EncryptTest {
    @Test
    public void testStringMD5() {
        String plaintext = "abcdef123456";
        String ciphertext = MD5Encrypt.stringMD5(plaintext);
        System.out.println(ciphertext);
    }
}
