package com.touzila.futurestaging.data.dao;

import com.touzila.futurestaging.api.FilterResult;
import com.touzila.futurestaging.api.GsonConversion;
import com.touzila.futurestaging.api.HttpHelper;
import com.touzila.futurestaging.api.params.BaseRequestParams;
import com.touzila.futurestaging.entity.LoginEntity;

import org.json.JSONObject;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * Created by liu on 2018/1/2.
 */

public class UserDao {

    public static Flowable<LoginEntity> login(BaseRequestParams params) {
        return HttpHelper.getResponse(params)
                .map(new FilterResult())
                .map(new GsonConversion<LoginEntity>() {
                    @Override
                    public LoginEntity apply(Object o) throws Exception {
                        return transform(o);
                    }
                });
    }
}
