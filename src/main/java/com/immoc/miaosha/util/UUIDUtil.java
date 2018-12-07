package com.immoc.miaosha.util;

import java.util.UUID;

/**
 * Created by buer on 2018/12/6.
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }
}
