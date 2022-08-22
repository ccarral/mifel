package com.mifel.service.crypto;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DefaultCipherTest {

    SecretKey secret;
    byte[] iv;
    byte[] msg;

    @BeforeEach
    public void setUp() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        this.secret = keyGenerator.generateKey();
        SecureRandom random = new SecureRandom();
        this.iv = new byte[16];
        random.nextBytes(iv);
        this.msg  = "Hola amigos, esta es una prueba".getBytes(StandardCharsets.UTF_8);
    }
    @Test
    public void testEncryptDecrypt() throws  IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] encriptado = DefaultCipher.encrypt(msg, secret , iv);
        byte[] desencriptado = DefaultCipher.decrypt(encriptado, secret, iv);
        assertArrayEquals(msg,desencriptado);
    }

    @Test
    void testBase64Encryption() throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] msg = "Hola amigos".getBytes(StandardCharsets.UTF_8);
        String msgBase64 = Base64DefaultCipher.encodeBase64(msg);
        String secretBase64 = Base64DefaultCipher.encodeBase64(secret.getEncoded());
        String base64EncodedOutput = Base64DefaultCipher.encrypt(msgBase64, secretBase64, iv);
        String originalMsgBase64 = Base64DefaultCipher.decrypt(base64EncodedOutput, secretBase64, iv);
        byte[] originalMsg = Base64DefaultCipher.decodeBase64(originalMsgBase64);
        assertArrayEquals(msg,originalMsg);
    }
}