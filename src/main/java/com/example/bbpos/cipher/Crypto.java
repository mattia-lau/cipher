package com.example.bbpos.cipher;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.buf.HexUtils;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.example.bbpos.cipher.exceptions.InvalidKeyLengthException;

public class Crypto {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static String encrypt(byte[] data, String key) throws Exception {
        if (data.length == 0) {
            return "";
        }

        byte[] keyBytes = key.getBytes();

        if (keyBytes.length != 32) {
            throw new InvalidKeyLengthException();
        }

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return HexUtils.toHexString(cipher.doFinal(data)).toUpperCase();
    }

    public static String decrypt(String cipherHex, String key) throws Exception {
        if (cipherHex.isEmpty()) {
            return "";
        }

        byte[] keyBytes = key.getBytes();

        if (keyBytes.length != 32) {
            throw new InvalidKeyLengthException();
        }

        byte[] encrypedData = HexUtils.fromHexString(cipherHex);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return new String(cipher.doFinal(encrypedData), "UTF-8");
    }
}