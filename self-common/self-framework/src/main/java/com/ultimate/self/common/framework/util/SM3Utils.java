package com.ultimate.self.common.framework.util;

import cn.hutool.core.util.StrUtil;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import java.io.UnsupportedEncodingException;
import java.security.Security;

/**
 * 描述：
 * 作者：kanwj
 * 日期：2025/10/20 14:58
 */
public class SM3Utils {

    static{
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ENCODEING = "UTF-8";

    public static String encrypt(String plainText){
        if(StrUtil.isBlank(plainText)){
            return null;
        }
        try{
            byte[] plainTextBytes = plainText.getBytes(ENCODEING);
            String cipherText = ByteUtils.toHexString(getSm3EncryptBytes(plainTextBytes));
            return cipherText;
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     获取SM3加密后的数组
     *@param bytes数组
     *@return
     */
    private static byte[] getSm3EncryptBytes(byte[] bytes){
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(bytes,0,bytes.length);
        byte[] sm3_bytes = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(sm3_bytes,0);
        return sm3_bytes;
    }
}


