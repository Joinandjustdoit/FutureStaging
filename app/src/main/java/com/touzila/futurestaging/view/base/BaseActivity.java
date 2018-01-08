package com.touzila.futurestaging.view.base;

import android.arch.lifecycle.LifecycleActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.touzila.futurestaging.R;
import com.touzila.futurestaging.utils.ActivityUtil;
import com.touzila.futurestaging.utils.StatusBarUtil;

/**
 * Created by liu on 2017/12/26.
 * 基类Activity
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivClose;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getContentViewId());
        super.onCreate(savedInstanceState);
        setStatusBar();
        ActivityUtil.addActivity(this);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        initTitleBar();
        initView();
        initData();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
    }

    private void initTitleBar() {
//        tvTitle.setText(getTitle());
//        ivBack.setOnClickListener(this);
//        ivClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_close:
                finish();
                break;
            default:
                break;
        }
    }

    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 隐藏软键盘
     * @param v
     */
    public void hideInputMethod(final EditText v) {
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }

    /**
     * 显示软键盘
     * @param v
     */
    public void showInputMethod(final EditText v) {
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v,0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtil.removeActivity(this);
    }
}
