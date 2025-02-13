package com.ultimate.self.auth.utils;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AESUtils {

    private static final String ALGORITHM = "AES/CFB/NoPadding";

    // 从配置文件中获取AES密钥
    private final static String aesKey = "sfa2139#sjdiSA11";

    public static String decrypt(String encryptedText) throws Exception {
        SecretKey key = new SecretKeySpec(aesKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(aesKey.getBytes()));
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

}
