package com.example.pockettutor;

import android.os.Build;
import androidx.annotation.RequiresApi;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private String algo = "AES";

    public String encrypt(String data) {
        try {
            String salt = "UrKk7E5m.ZhYXmg@";
            SecretKeySpec key = generateKey(salt);
            Cipher c = Cipher.getInstance(algo);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes());
            String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
            return encryptedValue;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }

    private SecretKeySpec generateKey(String data) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = data.getBytes("UTF-8");
            digest.update(bytes, 0, bytes.length);
            byte[] key = digest.digest();
            return new SecretKeySpec(key, "AES");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new SecretKeySpec(data.getBytes(), "AES");
    }
}