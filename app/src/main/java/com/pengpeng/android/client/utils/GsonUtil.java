package com.pengpeng.android.client.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title JsonUtil
 * @Package com.pengpeng.android.client.utils
 * @Description:
 * @date 2017/2/22 17:09
 */

public class GsonUtil {

    private static Gson gson;

    private static Gson getGson(){

        if (gson==null){
            synchronized (Gson.class){
                gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
            }
        }

        return gson;

    }

    public static String toJson(Object src) {
        return getGson().toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return getGson().fromJson(json, classOfT);
    }

}
