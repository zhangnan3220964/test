package com.zn.testmodule;

import android.view.View;
import android.webkit.WebView;

import com.zn.basemodule.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by zn on 2019-08-16.
 * Describe
 */
public class WebviewActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;
    @Override
    protected int getContentViewId() {
        return R.layout.web_view_layout;
    }

    @Override
    protected void initBundleData() {

    }
    String url = "file:///android_asset/html/" + "zadan.html";

    @Override
    protected void initView() {
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setBackgroundColor(0);
        webview.getBackground().setAlpha(0);

        webview.loadUrl(url);
    }

    @Override
    protected void initData() {

    }
}
