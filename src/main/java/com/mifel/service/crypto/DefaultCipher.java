package com.mifel.service.crypto;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DefaultCipher {
    public static byte[] encrypt(byte[] input, SecretKey secret, byte[] iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, secret , ivSpec);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException  | NoSuchPaddingException e) {
            // Esto NO debería de ocurrir
            System.err.println("No se encontró el algoritmo de encripción");
            System.exit(-1);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static byte[] decrypt(byte[] input, SecretKey secret, byte[] iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        try {
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secret, ivSpec);
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
            // Esto NO debería de ocurrir
            System.err.println("No se encontró el algoritmo de encripción o sus parametros son inválidos");
            System.exit(-1);
        }
        return null;
    }
}
