package com.touzila.futurestaging.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by liu on 2017/12/20.
 */

public interface RetrofitService {

    /**
     * post请求
     * @param requestBody
     * @return
     */
    @POST("weilaifenqi-api/rpcindex.do")
    Call<ResponseBody> post(@Body RequestBody requestBody);
}
