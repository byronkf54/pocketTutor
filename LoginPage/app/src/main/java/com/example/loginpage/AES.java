package com.example.loginpage;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private String algo = "AES";

    public String encrypt(String data) {
        SecretKeySpec key = generateKey(data);
        Cipher c = Cipher.getInstance(algo);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal();

    }

    private SecretKeySpec generateKey(String data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = data.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            byte[] key = digest.digest();
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            return secretKeySpec;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data.getBytes(), "AES");
    }

}