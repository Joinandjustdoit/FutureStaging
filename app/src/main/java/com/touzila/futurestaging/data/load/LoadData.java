package com.touzila.futurestaging.data.load;

/**
 * Created by liu on 2018/1/3.
 */

public interface LoadData<T> extends LoadView {

    void callData(T data);
}
