package com.touzila.futurestaging.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.touzila.futurestaging.R;

/**
 * Created by liu on 2017/12/26.
 */

public class WebViewActivity extends BaseActivity {

    private FrameLayout flRoot;
    private ProgressBar pb;
    private WebView webview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        flRoot = (FrameLayout) findViewById(R.id.ll_root);
        pb = findViewById(R.id.pb);
        webview = (WebView) findViewById(R.id.webview);

        setWebViewSettings();
        otherSetting();
    }

    private void otherSetting() {
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress <= 100) {
                    pb.setProgress(newProgress);
                    pb.setVisibility(View.VISIBLE);
                } else {
                    pb.setVisibility(View.GONE);
                }
            }
        });

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // TODO: 2017/12/26  String title = view.getTitle();
            }
        });
    }

    private void setWebViewSettings() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);        // 支持js交互
//        settings.setDomStorageEnabled(true);

        settings.setUseWideViewPort(true);          // 设置自适应屏幕，两者合用
        settings.setLoadWithOverviewMode(true);

        settings.setSupportZoom(true);              // 支持缩放，默认为true，是下面的前提
        settings.setBuiltInZoomControls(true);      // 设置内置的缩放控件，若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(false);     // 隐藏原生的缩放控件

        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAllowFileAccess(true);          // 设置可以访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO: 2017/12/26  WebBackForwardList list = webview.copyBackForwardList();
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webview != null) {
            webview.loadUrl(null);
            webview.clearHistory();
            flRoot.removeView(webview);
            webview.destroy();
            webview = null;
        }
    }
}
