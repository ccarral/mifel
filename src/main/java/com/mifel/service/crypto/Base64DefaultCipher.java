package com.mifel.service.crypto;


import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Base64;

public class Base64DefaultCipher {
    /**
     *
     * Encripta bytes arbitrarios con una clave de encripción Base 64 (url-safe) y regresa los bytes
     * en encriptados base 64 (no url-safe)
     *
     * @param msg Bytes que se desean encriptar
     * @param base64Secret Clave de encripción codificada en url-safe base 64
     * @param iv Vector de Inicialización
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static String encrypt(byte[] msg, String base64Secret, byte[] iv) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] keyBytes = Base64DefaultCipher.decodeBase64UrlSafe(base64Secret);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes,"AES");
        byte[] output = DefaultCipher.encrypt(msg, secretKeySpec,iv);
        return Base64DefaultCipher.encodeBase64(output);
    }

    /**
     * @param encrypted Bytes encriptados
     * @param base64Secret Clave de encripción en url-safe Base 64
     * @param iv Vector de inicialización
     * @return Bytes desencriptados en Base 64 (no url-safe)
     * @throws InvalidAlgorithmParameterException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     */
    public static String decrypt(byte[] encrypted, String base64Secret, byte[] iv) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        byte[] keyBytes = Base64DefaultCipher.decodeBase64UrlSafe(base64Secret);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes,"AES");
        byte[] output = DefaultCipher.decrypt(encrypted, secretKeySpec,iv);
        return Base64DefaultCipher.encodeBase64(output);
    }

    public static String encodeBase64UrlSafe(byte[] in){
        return  Base64.getUrlEncoder().encodeToString(in);
    }

    public static String encodeBase64(byte[] in){
        return  Base64.getEncoder().encodeToString(in);
    }

    public static byte[] decodeBase64UrlSafe(String in){
        return Base64.getUrlDecoder().decode(in);
    }

    public static byte[] decodeBase64(String in){
        return Base64.getDecoder().decode(in);
    }
}
