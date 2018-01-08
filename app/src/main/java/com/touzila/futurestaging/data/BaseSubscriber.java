package com.touzila.futurestaging.data;


import com.touzila.futurestaging.data.load.LoadView;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 用于在Http请求开始和结束时，页面的对应显示操作，并对错误进行统一处理
 */
public abstract class BaseSubscriber<T> implements Subscriber<T> {

    private LoadView loadDataView;

    public BaseSubscriber(LoadView loadDataView) {
        this.loadDataView = loadDataView;
    }


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Integer.MAX_VALUE);
        if (loadDataView != null) {
            loadDataView.onShowLoading();
            loadDataView.onHideRetry();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (loadDataView != null) loadDataView.onHideLoading();
        //if (BuildConfig.DEBUG) t.printStackTrace();
        if (loadDataView != null) {
            loadDataView.onHideLoading();
            loadDataView.onShowRetry();
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onNext(T t) {
        if (loadDataView != null) loadDataView.onHideLoading();
        onCallData(t);
    }

    protected abstract void onCallData(T t);
}