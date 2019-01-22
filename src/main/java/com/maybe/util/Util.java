package com.maybe.util;

import java.util.UUID;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by Maybe on 2019/1/22
 */
public class Util {
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
