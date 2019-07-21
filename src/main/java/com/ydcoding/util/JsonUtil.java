package com.ydcoding.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @program: honeysalesplatform
 * @description  将对象格式化json 格式
 * @author: ydcoding
 * @create: 2019-05-01 05:57
 **/
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
