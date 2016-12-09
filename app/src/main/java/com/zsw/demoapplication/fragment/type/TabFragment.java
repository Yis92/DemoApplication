package com.zsw.demoapplication.fragment.type;


import android.app.Activity;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.VideoActivity;
import com.zsw.demoapplication.adapter.MyListAdapter;
import com.zsw.demoapplication.entity.NewsContent;

import java.util.ArrayList;
import java.util.List;

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
            url="http://music.163.com/m/artist?id=161865&limit=12&offset=12#?thirdfrom=wx";
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
        final List<NewsContent> list = new ArrayList<>();
        list.add(new NewsContent("[生活]苍井空","http://player.youku.com/embed/XMzkyODEyMzY4"));
        list.add(new NewsContent("[娱乐]美国众女星卷入艳照门 詹妮弗-劳伦斯60张裸照外泄","http://player.youku.com/embed/XNzY3NDE2MDIw"));
        list.add(new NewsContent("[音乐]Trap Mix 2016 [ Best of Trap Music ]","http://player.youku.com/embed/XMTQ3MDg2MDg3Ng=="));
        list.add(new NewsContent("[搞笑]试浴室 美女恶搞 2016年11月2周","http://player.youku.com/embed/XMTgxNTY5ODAwOA=="));
        list.add(new NewsContent("Besame_2008_C_Split_7_1080_高清","http://player.youku.com/embed/XMTcxNzU4NTYwOA=="));
        list.add(new NewsContent("[拍客]日本性感美女写真002","http://player.youku.com/embed/XMTgzNDg1ODUyNA=="));
        list.add(new NewsContent("[生活]性感美女","http://player.youku.com/embed/XMTM3ODQxOTEyOA=="));
        list.add(new NewsContent("[拍客]性感美女背后摇 (114)_标清","http://player.youku.com/embed/XMTU3OTYzNDA5Mg=="));
        list.add(new NewsContent("[拍客]臀摇","http://player.youku.com/embed/XMTY1NTQ1NDg1Mg=="));
        list.add(new NewsContent("[拍客]美女牛仔性感紧身热舞","http://player.youku.com/embed/XMTQzMjMyNTE5Ng=="));
        list.add(new NewsContent("[拍客]妹子身材太好，就是皮裤勒太紧了.","http://player.youku.com/embed/XMTYzNTEyMTYyMA=="));
        list.add(new NewsContent("[生活]熊猫主播 美女 智敏儿 短裤 网丝 皮裤 舞蹈剪辑 2016 11 26","http://player.youku.com/embed/XMTgzNzM0OTQ4OA=="));
        ListView listview = (ListView) view.findViewById(R.id.my_listview);
        listview.setAdapter(new MyListAdapter(list,getActivity()));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }


}
