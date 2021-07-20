package com.gregperlinli.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author gregperlinli
 */
public class MD5Encrypt {

    /**
     * Encrypt string by MD5 method
     * @return encrypted text
     */
    public static String stringMD5(String input) {
        try {
            // Get an MD5 converter (change SHA1 parameter to "SHA1" if you want)
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // The input string is converted to a byte array
            byte[] inputByteArray = input.getBytes();
            // inputByteArray is an array of bytes converted from an input string
            messageDigest.update(inputByteArray);
            // Conversion and return results, is also a byte array, contains 16 elements
            byte[] resultByteArray = messageDigest.digest();
            // Character array converted to string returned
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
           return null;
        }
    }


    /**
     * Replace the byte array with a hexadecimal string
     * @param byteArray encrypt number
     * @return hexadecimal string
     */
    private static String byteArrayToHex(byte[] byteArray) {
        // Initialize a character array to store each hexadecimal character
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        // New a character array, which is used to form the result string
        // (explain: a byte is eight binary, that is, two hexadecimal characters (the eighth power of 2 is equal to the second power of 16))
        char[] resultCharArray =new char[byteArray.length * 2];
        // Traverses the byte array, through the bit operation (bit operation efficiency is high), transforms into the character to put in the character array
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        // Character arrays are returned as strings
        return new String(resultCharArray);
  }
}
