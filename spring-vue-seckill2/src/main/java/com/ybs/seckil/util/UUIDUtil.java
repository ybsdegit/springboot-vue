package com.ybs.seckil.util;

import java.util.UUID;

/**
 * UUIDUtil
 *
 * @author Paulson
 * @date 2020/3/13 1:40
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
