package com.zsw.demoapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zsw.demoapplication.R;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class VideoActivity extends Activity {
    //TODO 写到这里显示不了视频

    private WebView webview;
    private ProgressBar bar;
    private String url ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        webview = (WebView) findViewById(R.id.webview);
        bar = (ProgressBar)findViewById(R.id.progressbar);
        url = getIntent().getStringExtra("url");
        Log.e("qqq","url======"+url);
        WebSettings webset = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webset.setJavaScriptEnabled(false);
        webset.setPluginState(WebSettings.PluginState.ON);

        // 设置可以支持缩放
        webset.setSupportZoom(true);
        // 设置出现缩放工具
        webset.setBuiltInZoomControls(true);
        //不显示缩小镜和放大镜按钮
        webset.setDisplayZoomControls(false);
        //扩大比例的缩放
        webset.setUseWideViewPort(true);
        //自适应屏幕
        webset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webset.setLoadWithOverviewMode(true);
        //当页面中点击链接时，会调用这个方法
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //显示进度条
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        if(url!=null&!url.equals("")){
            //加载需要显示的网页
            webview.loadUrl("url");
        }else {
            webview.loadUrl("http://www.baidu.com");
        }

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//            //goBack()表示返回WebView的上一页面
//            webview.goBack();
//            return true;
//        }
//        return super.onKeyDown(keyCode,event);
//    }

    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            //goBack()表示返回WebView的上一页面
            webview.goBack();
        }else{
            super.onBackPressed();
        }
    }
}
