package com.touzila.futurestaging.http;

import android.accounts.NetworkErrorException;
import android.util.Base64;
import android.util.Log;

import com.touzila.futurestaging.BuildConfig;
import com.touzila.futurestaging.http.params.BaseRequestParams;
import com.touzila.futurestaging.utils.Base64Util;
import com.touzila.futurestaging.utils.RSAUtil;

import org.json.JSONObject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by liu on 2017/12/22.
 */

public class HttpHelper {

    private static String TAG = "HTTP";

    public static Flowable<String> getResponse(final BaseRequestParams params, final String method) {
        return Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                String requestParams = RequestParamsTransform.getRequestParams(params, method);
                log("加密前", requestParams);

                // rsa公钥加密
                byte[] rsaEncrypt = RSAUtil.encryptByPublicKey(requestParams.getBytes("UTF-8"));
                String afterBase64 = Base64.encodeToString(rsaEncrypt, Base64.DEFAULT);

                // 发请求
                Response<ResponseBody> response = RetrofitClient.createApi()
                        .post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), afterBase64))
                        .execute();

                // 服务器返回数据
                String backStr = response.body().string();
                log("服务器返回", backStr);

                if (response.isSuccessful()) {
                    // 服务器返回数据rsa公钥解密
                    byte[] rsaDecrypt = RSAUtil.decryptByPublicKey(Base64Util.decode(backStr));
                    JSONObject jsonObject = new JSONObject(new String(rsaDecrypt));
                    log("解密后", jsonObject.toString());

                    if (jsonObject.has("result") && jsonObject.optString("result") != null) {
                        JSONObject jo = new JSONObject(jsonObject.optString("result"));
                        if (jo.has("status") && jo.optString("status") != null) {
                            if (jo.optString("status").equals("SUCCESS")) {
                                if (jo.has("resultData") && jo.optString("resultData") != null) {
                                    e.onNext(jo.optString("resultData"));
                                } else {
                                    throwException("resultData not exist or resultData is null");
                                }
                            } else {
                                throwException("status is not SUCCESS");
                            }
                        } else {
                            throwException("status not exist or status is null");
                        }
                    } else {
                        throwException("result not exist or result is null");
                    }
                } else {
                    throwException(response.message());
                }
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
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
