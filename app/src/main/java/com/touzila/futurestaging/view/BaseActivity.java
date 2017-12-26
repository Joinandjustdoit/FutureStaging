package com.touzila.futurestaging.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.touzila.futurestaging.R;

/**
 * Created by liu on 2017/12/26.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivClose;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getContentViewId());
        super.onCreate(savedInstanceState);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        initTitleBar();
        initView();
        initData();
    }

    private void initTitleBar() {
        tvTitle.setText(getTitle());
        ivBack.setOnClickListener(this);
        ivClose.setOnClickListener(this);
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

}
