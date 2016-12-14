package com.zsw.demoapplication.activity.world;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.base.BaseActivity;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */
public class VideoActivity extends BaseActivity {

    private RelativeLayout rlBack;
    private WebView webview;
    private ProgressBar bar;
    private TextView title;
    private String url = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_video);
    }

    //解决关闭activity仍有声音的bug
    @Override
    public void onResume() {
        super.onResume();
        if (webview != null) {
            webview.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webview != null) {
            webview.onPause();
        }
    }

    @Override
    public void initBoot() {

    }

    @Override
    public void initViews() {
        String strTitle="内容";
        rlBack = findView(R.id.rl_back);
        webview = findView(R.id.webview);
        title = findView(R.id.tv_title);
        bar = findView(R.id.progressbar);
        title.setText(getString(R.string.video_title));
    }

    @Override
    public void initData(Intent intent) {
        url = intent.getStringExtra("url");

        if (!TextUtils.isEmpty(url)) {
            // 设置可以支持缩放
            webview.getSettings().setSupportZoom(true);
            // 设置出现缩放工具
            webview.getSettings().setBuiltInZoomControls(true);
            //自适应屏幕
            webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webview.getSettings().setLoadWithOverviewMode(true);
            //设置此属性，可任意比例缩放。
            //webview.getSettings().setUseWideViewPort(true);
            // 设置WebViewClient，保证新的链接地址不打开系统的浏览器窗口
            webview.setWebViewClient(new WebViewClient());
            // 设置WebView支持运行普通的Javascript
            webview.getSettings().setJavaScriptEnabled(true);

            // 设置WebChromeClient，以支持运行特殊的Javascript
            webview.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    bar.setProgress(newProgress);
                    if (newProgress == 100) {
                        bar.setVisibility(View.GONE);
                    }
                    super.onProgressChanged(view, newProgress);
                }

            });

            webview.loadUrl(url);
        }
    }

    @Override
    public void initEvents() {
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public Class<?> getClazz() {
        return VideoActivity.class;
    }
}
