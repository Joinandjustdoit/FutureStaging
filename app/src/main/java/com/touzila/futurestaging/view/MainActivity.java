package com.touzila.futurestaging.view;


import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.touzila.futurestaging.R;
import com.touzila.futurestaging.utils.StatusBarUtil;
import com.touzila.futurestaging.view.base.BaseActivity;
import com.touzila.futurestaging.view.home.HomeFragment;
import com.touzila.futurestaging.view.my.MyFragment;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements LifecycleRegistryOwner, HasSupportFragmentInjector {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    private LinearLayout llBottomNavigation;
    private ArrayList<Fragment> fragments;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = llBottomNavigation.indexOfChild(v);
            changeUi(index);
            changeFragment(index);
        }
    };

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageViewInFragment(this,null);
    }

    @Override
    protected void initView() {
        llBottomNavigation = (LinearLayout) findViewById(R.id.ll_bottom_navigation);
        initFragment();
        setListener();
    }

    @Override
    protected void initData() {
    }


    /**
     * 初始化fragmnet
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        fragments.add(new MyFragment());
        onClickListener.onClick(llBottomNavigation.getChildAt(0));
    }

    /**
     * 设置底部导航的点击事件
     */
    private void setListener() {
        int childCount = llBottomNavigation.getChildCount();
        for (int i = 0; i < childCount; i++) {
            FrameLayout child = (FrameLayout) llBottomNavigation.getChildAt(i);
            child.setOnClickListener(onClickListener);
        }
    }

    private void changeUi(int index) {
        int childCount = llBottomNavigation.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i == index) {     // 不可点击
                setEnable(llBottomNavigation.getChildAt(i), false);
            } else {
                setEnable(llBottomNavigation.getChildAt(i), true);
            }
        }
    }

    /**
     * 设置view是否可点击
     * 将每个Item中的所有控件状态一起改变
     * 由于Item可能会有很多层，所以需要使用递归
     * @param view
     * @param b
     */
    private void setEnable(View view, boolean b) {
        view.setEnabled(b);
        if (view instanceof ViewGroup) {
            int childCount = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < childCount; i++) {
                setEnable(((ViewGroup) view).getChildAt(i), b);
            }
        }
    }

    /**
     * 切换fragment
     * @param index
     */
    private void changeFragment(int index) {
        Fragment fragment = fragments.get(index);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
    }

}
