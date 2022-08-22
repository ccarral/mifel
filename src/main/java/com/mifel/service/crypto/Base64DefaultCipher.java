package com.mifel.service.crypto;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Base64DefaultCipher {
    public static String encrypt(byte[] msg, String base64Secret, byte[] iv) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] keyBytes = Base64DefaultCipher.decodeBase64(base64Secret);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes,"AES");
        byte[] output = DefaultCipher.encrypt(msg, secretKeySpec,iv);
        return Base64DefaultCipher.encodeBase64(output);
    }

    public static String decrypt(byte[] encrypted, String base64Secret, byte[] iv) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] keyBytes = Base64DefaultCipher.decodeBase64(base64Secret);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes,"AES");
        byte[] output = DefaultCipher.decrypt(encrypted, secretKeySpec,iv);
        return Base64DefaultCipher.encodeBase64(output);
    }

    public static String encodeBase64(byte[] in){
        return  Base64.getUrlEncoder().encodeToString(in);
    }

    public static byte[] decodeBase64(String in){
        return Base64.getUrlDecoder().decode(in);
    }
}
