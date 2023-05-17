package com.example.cipher;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil{
        private static final String SECRET_KEY = "MySecretKey123";
        private static final String SALT = "MySalt456";
        private static final String INIT_VECTOR = "MyInitializationVector789";


        public static String encrypt(String plainText, Context c) {
                try {
                        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        PBEKeySpec pbeKeySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 1000, 256);
                        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
                        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

                        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

                        return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
                } catch (Exception e) {
                        e.printStackTrace();
                        // Handle encryption error
                }
                return null;
        }

        public static String decrypt(String encryptedText,Context c) {
                try {
                        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
                        PBEKeySpec pbeKeySpec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 1000, 256);
                        SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
                        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getEncoded(), "AES");

                        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                        IvParameterSpec ivParameterSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

                        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
                        byte[] decryptedBytes = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT));

                        return new String(decryptedBytes, StandardCharsets.UTF_8);
                } catch (Exception e) {
                        e.printStackTrace();
                        // Handle decryption error
                }
                return null;
        }
}

