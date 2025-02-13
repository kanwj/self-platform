package com.ultimate.self.common.security.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: MD5工具类
 * @Author: Chuanlei.Sun
 * @Date: Created in 2018-08-26 20:09
 */
@Slf4j
public class MD5Util {

    /**
     * 生成身份验证加密字符串
     *
     * @author Chuanlei.Sun
     * @date 2018/10/28 23:30
     * @param identityKey 身份秘钥
     * @param toEncrypts 待加密字符串
     * @return java.lang.String
     */
    public static String initIdentity(String identityKey, String toEncrypts){
        if (StringUtils.isAnyBlank(identityKey,toEncrypts)) {
            log.warn(">>>>>>>>>初始化身份验证加密字符失败，传入参数存在空字符，身份秘钥:{}, 待加密字符串:{}",identityKey,toEncrypts);
            return null;
        }
        return encode(identityKey + toEncrypts);
    }

    /**
     * 验证身份信息
     *
     * @author Chuanlei.Sun
     * @date 2018/10/28 23:30
     * @param identityKey 身份秘钥
     * @param toEncrypts 待加密字符串
     * @param encrypted 身份验证字符串
     * @return boolean
     */
    public static boolean authentication(String identityKey, String toEncrypts, String encrypted){
        if (StringUtils.isAnyBlank(identityKey,toEncrypts)) {
            log.warn(">>>>>>>>>验证身份信息失败，传入参数存在空字符，身份秘钥:{}, 待加密字符串:{},身份验证字符串:{}",identityKey,toEncrypts,encrypted);
            return false;
        }
        String authStr = encode(identityKey + toEncrypts);
        log.info(">>>>>>>>>传入身份验证字符:{},目标身份验证字符:{}",encrypted,authStr);
        return encrypted.equals(authStr);
    }

    public static String encodeByMD5(String originString) throws NoSuchAlgorithmException {
        if (originString != null) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] result = md.digest(originString.getBytes());
            String resultString = byteArrayToHexString(result);
            return resultString.toUpperCase();
        }
        return null;
    }
    private static String byteArrayToHexString(byte[] result) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aResult : result) {
            resultSb.append(byteToHexString(aResult));
        }
        return resultSb.toString();
    }
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 加密字符串
     *
     * @param plainText 字符串
     * @return java.lang.String
     * @author Chuanlei.Sun
     * @date 2018/10/28 23:30
     */
    public static String encode(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuilder buf = new StringBuilder("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            log.info("32位的加密:{} ",buf.toString());
            log.info("16位的加密:{} ",buf.substring(8, 24));
            return buf.toString();
        } catch (Exception e) {
            log.error("加密异常:{} ",e.getMessage());
            e.printStackTrace();
        }
        return "";
    }
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};


    public static void main(String[] args) {
        System.out.println(MD5Util.initIdentity("qiaqiafood20210416" , "2021120717092820198765"));
    }
}
