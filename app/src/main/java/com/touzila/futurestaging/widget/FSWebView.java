package com.touzila.futurestaging.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by liu on 2017/12/26.
 * WebView封装
 */

public class FSWebView extends WebView {

    public FSWebView(Context context) {
        super(context);
    }

    public FSWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FSWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FSWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public FSWebView(Context context, AttributeSet attrs, int defStyleAttr, boolean privateBrowsing) {
        super(context, attrs, defStyleAttr, privateBrowsing);
    }

    private void setWebViewSettings() {

        setWebChromeClients();
    }

    private void setWebChromeClients() {

    }
}
