package com.touzila.futurestaging.api;

import android.accounts.NetworkErrorException;
import android.arch.lifecycle.LiveData;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.touzila.futurestaging.BuildConfig;
import com.touzila.futurestaging.api.params.BaseRequestParams;
import com.touzila.futurestaging.utils.Base64Util;
import com.touzila.futurestaging.utils.RSAUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by liu on 2017/12/22.
 */

public class HttpHelper {

    private static final String rpc = "1.0";
    private static String TAG = "HTTP";

    public static Flowable<String> getResponse(final BaseRequestParams params) {
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

//                String requestParams = requestParamsTransform(params, method);
                String requestParams = requestParamsTransform(params);
                log("加密前", requestParams);

                // rsa公钥加密
                byte[] rsaEncrypt = RSAUtil.encryptByPublicKey(requestParams.getBytes("UTF-8"));
                String afterBase64 = Base64.encodeToString(rsaEncrypt, Base64.DEFAULT);

                Response<ResponseBody> response = null;
                // 发请求
                try {
                    response = RetrofitClient.createApi()
                            .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), afterBase64))
                            .execute();
                } catch (Exception e1) {
                    if (e1 instanceof SocketTimeoutException) {
                        String backStr = "HtagNedkNNL2cLYMLCVpFd+Hq9P7cqjZcc2CwP4b2EoNiD06bBBSR7mq4Ni+DTC/G2q4EqjyqG3p\n" +
                                "Xpaux0QYZzJVeaUiscYjvLp3RpdARxF4jfoZOuCCoh/Qg/OskCchuZPWWesPWET+PtHYFGuzxRuY\n" +
                                "2ZdJkzBnIJnP5ZPqc0YVveRwJZGZi4Ch7uP3KniHxfMLtuWY/PM2/dNKtEu0EIuJ+lVEU36YQbSL\n" +
                                "kusBYu26lWzFYO+i3GTrUVASuxKm2VztA3qBeixF5WS2UZjGsAbSosh/kv/+UMgtgxYdT/ynlUxq\n" +
                                "buHOuM21GllOkjrgS2P8Dl7iOmULu3CcMsb6Lg==";
                        log("服务器返回", backStr);
                        byte[] rsaDecrypt = RSAUtil.decryptByPublicKey(Base64Util.decode(backStr));
                        JSONObject jsonObject = new JSONObject(new String(rsaDecrypt));
                        log("解密后", jsonObject.toString());
                        if (jsonObject.has("result") && jsonObject.optString("result") != null) {
                            JSONObject jo = new JSONObject(jsonObject.optString("result"));
                            if (jo.has("status") && jo.optString("status") != null) {
//                                if (jo.optString("status").equals("SUCCESS")) {
                                if (jo.optString("status").equals("FAILURE")) {
                                    e.onNext(jsonObject.toString());
                                } else {
                                    throwException("status is not SUCCESS");
                                }
                            } else {
                                throwException("status not exist or status is null");
                            }
                        } else {
                            throwException("result not exist or result is null");
                        }
                    }
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
//                Response<ResponseBody> response = RetrofitClient.createApi()
//                        .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), afterBase64))
//                        .execute();

                // 服务器返回数据
//                String backStr = response.body().string();
//                String backStr = "HtagNedkNNL2cLYMLCVpFd+Hq9P7cqjZcc2CwP4b2EoNiD06bBBSR7mq4Ni+DTC/G2q4EqjyqG3p\n" +
//                        "Xpaux0QYZzJVeaUiscYjvLp3RpdARxF4jfoZOuCCoh/Qg/OskCchuZPWWesPWET+PtHYFGuzxRuY\n" +
//                        "2ZdJkzBnIJnP5ZPqc0YVveRwJZGZi4Ch7uP3KniHxfMLtuWY/PM2/dNKtEu0EIuJ+lVEU36YQbSL\n" +
//                        "kusBYu26lWzFYO+i3GTrUVASuxKm2VztA3qBeixF5WS2UZjGsAbSosh/kv/+UMgtgxYdT/ynlUxq\n" +
//                        "buHOuM21GllOkjrgS2P8Dl7iOmULu3CcMsb6Lg==";
//                log("服务器返回", backStr);

//                if (response.code() != 200) {
//                    // 服务器返回数据rsa公钥解密
//                    byte[] rsaDecrypt = RSAUtil.decryptByPublicKey(Base64Util.decode(backStr));
//                    JSONObject jsonObject = new JSONObject(new String(rsaDecrypt));
//                    log("解密后", jsonObject.toString());
////                    e.onNext(jsonObject.toString());
//                    if (jsonObject.has("result") && jsonObject.optString("result") != null) {
//                        JSONObject jo = new JSONObject(jsonObject.optString("result"));
//                        if (jo.has("status") && jo.optString("status") != null) {
//                            if (jo.optString("status").equals("SUCCESS")) {
//                                if (jo.has("resultData") && jo.optString("resultData") != null) {
//                                    e.onNext(jo.optString("resultData"));
//                                } else {
//                                    throwException("resultData not exist or resultData is null");
//                                }
//                            } else {
//                                throwException("status is not SUCCESS");
//                            }
//                        } else {
//                            throwException("status not exist or status is null");
//                        }
//                    } else {
//                        throwException("result not exist or result is null");
//                    }
//                } else {
//                    throwException(response.message());
//                }
//                e.onComplete();
//            }
//        }, BackpressureStrategy.BUFFER);
    }


    /**
     * 返回json类型字符串的请求参数
     * @param params
//     * @param method
     * @return
     */
    private static String requestParamsTransform(BaseRequestParams params/*, String method*/) {
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
//            jo.put("method", method);
            jo.put("method", params.getMethod());
            jo.put("id", System.currentTimeMillis());
            jo.put("jsonrpc", rpc);
            jo.put("params", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jo.toString();
    }


    /**
     * 异常
     * @param message
     * @throws NetworkErrorException
     */
    private static void throwException(String message) throws NetworkErrorException {
        throw new NetworkErrorException(message);
    }


    /**
     * 日志
     * @param tag
     * @param s
     */
    private static void log(String tag, String s) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG.concat(tag), s);
        }
    }

}
