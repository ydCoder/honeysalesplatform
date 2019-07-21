package com.ydcoding.util;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-12 22:53
 **/
public class MD5Util {
    /**
     * @Description: 对字符串进行md5加密
     */
    public static String getMD5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String newstr = Base64.encodeBase64String(md5.digest(strValue.getBytes()));
        return newstr;
    }
}
