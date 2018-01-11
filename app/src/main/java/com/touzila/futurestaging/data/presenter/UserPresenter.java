package com.touzila.futurestaging.data.presenter;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.touzila.futurestaging.api.params.BaseRequestParams;
import com.touzila.futurestaging.data.BaseSubscriber;
import com.touzila.futurestaging.data.dao.UserDao;
import com.touzila.futurestaging.data.load.LoadData;
import com.touzila.futurestaging.entity.LoginEntity;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liu on 2018/1/2.
 */
@Singleton
public class UserPresenter {

//    @Inject
//    public UserPresenter() {
//    }

    public static <T> Flowable<T> executeThread(Flowable<T> flowable) {
        return flowable.subscribeOn(Schedulers.newThread())     //操作运行在线程中
                .observeOn(AndroidSchedulers.mainThread());     //通知观察者时在main线程;
    }

//    public static void login(BaseRequestParams params, final LoadData<LoginEntity> loadData) {
    public static void login(BaseRequestParams params, final LoadData<LiveData<LoginEntity>> loadData) {
        executeThread(UserDao.login(params)).subscribe(new BaseSubscriber<LoginEntity>(loadData) {
            @Override
            protected void onCallData(LoginEntity loginEntity) {
                MutableLiveData<LoginEntity> liveData = new MutableLiveData<>();
                liveData.setValue(loginEntity);
                liveData.postValue(loginEntity);
                loadData.callData(liveData);
//                loadData.callData(loginEntity);
            }
        });
    }

    public static void logins(BaseRequestParams params, final LoadData<LoginEntity> loadData) {
        executeThread(UserDao.login(params)).subscribe(new BaseSubscriber<LoginEntity>(loadData) {
            @Override
            protected void onCallData(LoginEntity loginEntity) {
                loadData.callData(loginEntity);
            }
        });
    }
}
