package com.demo.liuhf.mybaselibrarys.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.alibaba.fastjson.JSON;

/**
 * 创建人：liuhaifeng
 * 时间：2018/4/24.
 * 描述：
 * 修改历史：
 */

public class JsonUtil {
    public static  <T> T ObjForStr(String text, Class<T> clazz) {
        if (text == null || text.isEmpty()) {
            return null;
        }
        try {
            return JSON.parseObject(text, clazz);
        } catch (Exception e) {
            return null;
        }

    }

    public static String StrForObj(Object clazz) {
        if (null != clazz) {
            return JSON.toJSONString(clazz);
        }
        return "";
    }


}
