package com.example.game.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ Author   ：yangyunlong.
 * @ Date     ：Created in 13:59 2019/2/19
 * @Version ： $version$
 */
public class EncodeByMd5Util {
    public static String encodeByMd5(String str) {
        //加密算法
        MessageDigest ms = null;
        try {
            ms = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String newStr = null;
        try {
            newStr = base64Encoder.encode(ms.digest(str.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newStr;
    }
}
