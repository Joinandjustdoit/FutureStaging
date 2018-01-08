package com.touzila.futurestaging.api;


import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.functions.Function;

/**
 * Created by liu on 2018/1/8.
 */

public class FilterResult implements Function<String, Object> {

    @Override
    public Object apply(String input) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(input);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Object o = jsonObject.opt("result");
        return o;
    }
}
