package com.zsw.demoapplication.fragment.type;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
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

public class TabFragment extends Fragment {
    private View view;
    private String title;

    public static TabFragment newInstance(String title) {
        TabFragment f = new TabFragment();
        //很重要
        f.setTitle(title);
        return f;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab, null);
        ViewStub vsTitle = (ViewStub) view.findViewById(R.id.vs_title);
        ViewStub vsWeb = (ViewStub) view.findViewById(R.id.vs_web);
        String url = "";
        if("华语".equals(title)){
            vsWeb.inflate();
            url="http://music.163.com/#/search/m/?id=427145&s=%E8%94%A1%E4%BE%9D%E6%9E%97&type=1004";
            initWebData(url);
        }else if("欧美".equals(title)){
            vsWeb.inflate();
            url="http://music.163.com/#/search/m/?id=46487&s=%E5%B8%83%E5%85%B0%E5%A6%AE&type=1004";
            initWebData(url);
        }else if("日语".equals(title)){
            vsWeb.inflate();
            url="http://music.163.com/#/artist/mv?id=161865&limit=12&offset=0";
            initWebData(url);
        }else if("韩语".equals(title)){
            vsWeb.inflate();
            url="http://music.163.com/#/search/m/?id=161865&limit=12&offset=12&s=%E6%9D%8E%E5%AD%9D%E5%88%A9&type=1004";
            initWebData(url);
        }else{
            vsTitle.inflate();
            initListData();
        }
        return view;
    }



    private void initWebData(String url) {
        WebView webview = (WebView) view.findViewById(R.id.my_webview);
        final ProgressBar bar = (ProgressBar)view.findViewById(R.id.myProgressBar);
        WebSettings webset = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webset.setJavaScriptEnabled(true);
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
        //加载需要显示的网页
        webview.loadUrl(url);
    }

    private void initListData() {

    }


}
