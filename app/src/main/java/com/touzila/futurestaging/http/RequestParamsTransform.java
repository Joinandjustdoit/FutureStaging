package com.touzila.futurestaging.http;

import com.google.gson.Gson;
import com.touzila.futurestaging.http.params.BaseRequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by liu on 2017/12/20.
 * 请求参数转换
 */

public class RequestParamsTransform {


    /**
     * 返回json类型字符串的请求参数
     * @param params
     * @param method
     * @return
     */
    public static String getRequestParams(BaseRequestParams params, String method) {
        Gson gson = new Gson();
        String s = gson.toJson(params);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        JSONObject jo = new JSONObject();
        try {
            jo.put("method", method);
            jo.put("id", System.currentTimeMillis());
            jo.put("jsonrpc", "1.0");
            jo.put("params", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jo.toString();
    }
}
