package com.touzila.futurestaging.data.load;

/**
 * Created by liu on 2018/1/3.
 */

public interface LoadView {

    void onShowLoading();

    void onHideLoading();

    void onShowRetry();

    void onHideRetry();

    void onError(Exception e);
}
