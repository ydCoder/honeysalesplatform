package com.ydcoding.util;

import com.ydcoding.emums.StatusEnum;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-02-03 13:05
 **/
public class EnumUtil {
    public static <T extends StatusEnum> T getByStatus(Integer status, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (status.equals(each.getStatus())) {
                return each;
            }
        }
        return null;
    }
}
