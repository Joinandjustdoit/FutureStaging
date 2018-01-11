package com.touzila.futurestaging.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.touzila.futurestaging.api.params.LoginRequestParams;
import com.touzila.futurestaging.data.AbsentLiveData;
import com.touzila.futurestaging.data.load.LoadDataImpl;
import com.touzila.futurestaging.data.presenter.UserPresenter;
import com.touzila.futurestaging.entity.LoginEntity;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by liu on 2018/1/2.
 */

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginRequestParams> loginRequestParams = new MutableLiveData<>();

    private LiveData<LoginEntity> loginEntityLiveData;

    private LoginEntity loginEntity;

    @Inject
    public LoginViewModel() {
//        loginEntityLiveData = Transformations.switchMap(loginRequestParams, new Function<LoginRequestParams, LiveData<LoginEntity>>() {
//            @Override
//            public LiveData<LoginEntity> apply(LoginRequestParams input) {
//                if (input == null) {
//                    return AbsentLiveData.create();
//                } else {
//                    try {
//                        UserPresenter.login(input, new LoadDataImpl<LiveData<LoginEntity>>() {
//                            @Override
//                            public void callData(LiveData<LoginEntity> data) {
//                                loginEntityLiveData = data;
//                            }
//                        });
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                return loginEntityLiveData;
//            }
//        });
        Transformations.map(loginRequestParams, new Function<LoginRequestParams, LoginEntity>() {
            @Override
            public LoginEntity apply(LoginRequestParams input) {
                if (input == null) {
                    return null;
                } else {
                    UserPresenter.logins(input, new LoadDataImpl<LoginEntity>() {
                        @Override
                        public void callData(LoginEntity data) {
                            loginEntity = data;
                        }
                    });
                    return loginEntity;
                }
            }
        });
    }

    public LoginEntity getLoginEntity() {
        return loginEntity;
    }

    public LiveData<LoginEntity> getLoginEntityLiveData() {
        return loginEntityLiveData;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setLoginParams(LoginRequestParams params) {
        if (Objects.equals(params, loginRequestParams.getValue())) {
            return;
        }
        loginRequestParams.setValue(params);
    }
}
