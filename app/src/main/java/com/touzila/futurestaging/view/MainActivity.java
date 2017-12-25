package com.touzila.futurestaging.view;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.touzila.futurestaging.R;
import com.touzila.futurestaging.utils.ToastUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                ToastUtil.showShortToastBottom("showShortToast");
                break;
            case R.id.btn2:
                ToastUtil.showShortToastCenter("showShortToastCenter");
                break;
            case R.id.btn3:
                ToastUtil.showShortToastTop("showShortToastTop");
                break;
            case R.id.btn4:
                ToastUtil.showLongToastBottom("showLongToast");
                break;
            case R.id.btn5:
                ToastUtil.showLongToastCenter("showLongToastCenter");
                break;
            case R.id.btn6:
                ToastUtil.showLongToastTop("showLongToastTop");
                break;
        }
    }
}
