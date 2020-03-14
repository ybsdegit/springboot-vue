package com.ybs.seckil.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5Util
 *
 * @author Paulson
 * @date 2020/3/12 23:08
 */
public class MD5Util {

    public static String salt = "springboot";

    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    public static String backToDb(String str, String dbSalt){
        return DigestUtils.md5Hex(str+dbSalt);
    }
}
