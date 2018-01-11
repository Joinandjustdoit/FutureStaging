package com.touzila.futurestaging.view.home;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.touzila.futurestaging.R;
import com.touzila.futurestaging.api.params.LoginRequestParams;
import com.touzila.futurestaging.binding.FragmentDataBindingComponent;
import com.touzila.futurestaging.data.load.LoadData;
import com.touzila.futurestaging.data.load.LoadDataImpl;
import com.touzila.futurestaging.data.presenter.UserPresenter;
import com.touzila.futurestaging.databinding.FragmentHomeBinding;
import com.touzila.futurestaging.di.Injectable;
import com.touzila.futurestaging.entity.LoginEntity;
import com.touzila.futurestaging.utils.DeviceInfoUtil;
import com.touzila.futurestaging.viewmodel.LoginViewModel;
import javax.inject.Inject;

/**
 * Created by liu on 2017/12/26.
 * 首页--Fragment
 */

public class HomeFragment extends LifecycleFragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);

    private LoginViewModel loginViewModel;

    private FragmentHomeBinding binding;
    private LoginRequestParams params;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false, dataBindingComponent);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

        params = new LoginRequestParams();
        params.setAgentNum(DeviceInfoUtil.getDeviceId());
        params.setMethod("userAccountService.login");
        params.setChannel("Android");
        params.setCode("login");
        params.setPassword("123456");
        params.setSources("Android");
        params.setSystem(DeviceInfoUtil.getSystem());
        params.setTelephone("18401238692");
        params.setTimestamp(System.currentTimeMillis());

//        loginViewModel.setLoginParams(params);
//        loginViewModel.getLoginEntityLiveData().observe(this, new Observer<LoginEntity>() {
//            @Override
//            public void onChanged(@Nullable LoginEntity loginEntity) {
//                Log.d("login", loginEntity.toString());
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        loginViewModel.setLoginParams(params);
        loginViewModel.getLoginEntityLiveData().observe(this, new Observer<LoginEntity>() {
            @Override
            public void onChanged(@Nullable LoginEntity loginEntity) {
                Log.d("login", loginEntity.toString());
            }
        });

//        UserPresenter.logins(params, new LoadDataImpl<LoginEntity>() {
//            @Override
//            public void callData(LoginEntity data) {
//                Log.d("login", data.toString());
//            }
//        });
    }
}
