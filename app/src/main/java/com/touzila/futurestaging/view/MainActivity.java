package com.touzila.futurestaging.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.touzila.futurestaging.R;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

//    private FrameLayout flContainer;
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
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        flContainer = (FrameLayout) findViewById(R.id.fl_container);
        llBottomNavigation = (LinearLayout) findViewById(R.id.ll_bottom_navigation);

        initFragment();
        setListener();
    }


    /**
     * 初始化fragmnet
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new Home0Fragment());
        fragments.add(new Home1Fragment());
        fragments.add(new Home2Fragment());
        fragments.add(new Home3Fragment());
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
