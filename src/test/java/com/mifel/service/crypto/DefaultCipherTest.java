package com.mifel.service.crypto;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultCipherTest {

    @Test
    void testEncryptDecrypt() throws NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secret = keyGenerator.generateKey();
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        byte[] input = "Hola amigos, esta es una prueba".getBytes(StandardCharsets.UTF_8);
        byte[] encriptado = DefaultCipher.encrypt(input, secret , iv);
        byte[] desencriptado = DefaultCipher.decrypt(encriptado, secret, iv);
        assertArrayEquals(input,desencriptado);
    }
}