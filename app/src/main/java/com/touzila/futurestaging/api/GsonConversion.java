package com.touzila.futurestaging.api;


import android.arch.lifecycle.LiveData;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.functions.Function;


public abstract class GsonConversion<T> implements Function<Object, T> {

    public static Gson mGson = new Gson();

    /**
     * 获取T的类型
     * @return
     */
    public Type getType() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) t;
        return p.getActualTypeArguments()[0];
    }


    /**
     * 将json转换为对应T的对象，需要手动调用
     * @param data
     * @return
     */
    public T transform(Object data) throws Exception {
        if (data instanceof JSONObject || data instanceof JSONArray) {
            return mGson.fromJson(data.toString(), getType());
        } else {
            return (T) data;
        }
    }


}
