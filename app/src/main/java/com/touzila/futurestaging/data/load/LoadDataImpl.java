package com.touzila.futurestaging.data.load;

/**
 * Created by liu on 2018/1/3.
 */

public abstract class LoadDataImpl<T> implements LoadData<T> {

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowRetry() {

    }

    @Override
    public void onHideRetry() {

    }

    @Override
    public void onError(Exception e) {

    }
}
