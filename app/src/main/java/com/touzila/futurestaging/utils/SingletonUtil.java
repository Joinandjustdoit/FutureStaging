package com.touzila.futurestaging.utils;

/**
 * Created by liu on 2017/12/25.
 */

public abstract class SingletonUtil<T> {

    private T instance;

    protected abstract T newInstance();

    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtil.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }

}
