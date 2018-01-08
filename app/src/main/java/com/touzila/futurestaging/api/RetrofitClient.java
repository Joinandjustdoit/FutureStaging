package com.touzila.futurestaging.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * Created by liu on 2017/12/21.
 */

public class RetrofitClient {

    private static String BASE_URL = "http://192.168.3.148:8080/";      // 服务器地址
    private static int TIMEOUT_READ_WRITE = 20;                         // 读写超时
    private static int TIMEOUT_CONNECT = 10;                            // 链接超时

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ_WRITE, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ_WRITE, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();

    public static RetrofitService createApi() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(RetrofitService.class);
    }
}
