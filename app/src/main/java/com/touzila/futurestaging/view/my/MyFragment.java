package com.touzila.futurestaging.view.my;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;

import com.touzila.futurestaging.R;
import com.touzila.futurestaging.di.Injectable;
import com.touzila.futurestaging.utils.ScreenUtil;
import com.touzila.futurestaging.utils.StatusBarUtil;
import com.touzila.futurestaging.view.base.BaseFragment;

/**
 * Created by liu on 2017/12/26.
 * 我的--Fragment
 */

public class MyFragment extends BaseFragment implements Injectable {

    private View fakeStatusBar;



    @Override
    protected int getlayoutId() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initView() {
        fakeStatusBar = view.findViewById(R.id.fake_status_bar);
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) fakeStatusBar.getLayoutParams();
//        params.height = ScreenUtil.dpToPxInt(getActivity(), StatusBarUtil.getStatusBarHeight(getActivity()));
        params.height = ScreenUtil.pxToDpCeilInt(getActivity(), StatusBarUtil.getStatusBarHeight(getActivity()));
        fakeStatusBar.setLayoutParams(params);

    }

    @Override
    protected void initData() {

    }

    public void setTvTitleBackgroundColor() {
    }
}
