package com.zsw.demoapplication.fragment.type;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.world.VideoActivity;
import com.zsw.demoapplication.adapter.MyListAdapter;
import com.zsw.demoapplication.base.BaseFragment;
import com.zsw.demoapplication.entity.NewsContent;
import com.zsw.demoapplication.widget.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class TabFragment2 extends BaseFragment {

    private String title;

    private LinearLayout llTitle;
    private LinearLayout llWeb;
    private RefreshLayout mRefreshLayout;
    private final List<NewsContent> list = new ArrayList<>();
    private MyListAdapter myListAdapter;

    public static TabFragment2 newInstance(String title) {
        TabFragment2 f = new TabFragment2();
        //很重要
        f.setTitle(title);
        return f;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public View bootView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_tab, viewGroup, false);
    }

    @Override
    public void initBoot() {

    }

    @Override
    public void initViews() {
        llTitle = findView(R.id.ll_title);
        llWeb = findView(R.id.ll_web);
        mRefreshLayout = findView(R.id.srl_refresh);

        //设置刷新的颜色
        mRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light));

    }

    @Override
    public void initData(Bundle bundle) {
        String url = "";
        if ("华语".equals(title)) {
            llWeb.setVisibility(View.VISIBLE);
            url = "http://music.163.com/#/search/m/?id=427145&s=%E8%94%A1%E4%BE%9D%E6%9E%97&type=1004";
            initWebData(url);
        } else if ("欧美".equals(title)) {
            llWeb.setVisibility(View.VISIBLE);
            url = "http://music.163.com/#/search/m/?id=46487&s=%E5%B8%83%E5%85%B0%E5%A6%AE&type=1004";
            initWebData(url);
        } else if ("日语".equals(title)) {
            llWeb.setVisibility(View.VISIBLE);
            url = "http://music.163.com/m/artist?id=161865&limit=12&offset=12#?thirdfrom=wx";
            initWebData(url);
        } else if ("韩语".equals(title)) {
            llWeb.setVisibility(View.VISIBLE);
            url = "http://music.163.com/#/search/m/?id=161865&limit=12&offset=12&s=%E6%9D%8E%E5%AD%9D%E5%88%A9&type=1004";
            initWebData(url);
        } else {
            llTitle.setVisibility(View.VISIBLE);
            initListData();
        }
    }

    @Override
    public void initEvents() {
        // 设置下拉刷新监听器
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //获得新的数据
                        list.add(new NewsContent("这是下拉刷新的item", ""));
                        //更新数据
                        myListAdapter.notifyDataSetChanged();
                        //停止刷新动画
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        // 设置上拉加载监听器
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //获得新的数据
                        list.add(new NewsContent("这是下拉刷新的item", ""));
                        //更新数据
                        myListAdapter.notifyDataSetChanged();
                        // 加载完后调用该方法停止动画
                        mRefreshLayout.setLoading(false);
                    }
                },2000);
            }
        });
    }

    private void initWebData(String url) {
        WebView webview = (WebView) view.findViewById(R.id.my_webview);
        final ProgressBar bar = (ProgressBar) view.findViewById(R.id.myProgressBar);
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
        webview.setWebViewClient(new WebViewClient() {
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
        list.add(new NewsContent("[生活]苍井空", "http://player.youku.com/embed/XMzkyODEyMzY4"));
        list.add(new NewsContent("[娱乐]美国众女星卷入艳照门 詹妮弗-劳伦斯60张裸照外泄", "http://player.youku.com/embed/XNzY3NDE2MDIw"));
        list.add(new NewsContent("[音乐]Trap Mix 2016 [ Best of Trap Music ]", "http://player.youku.com/embed/XMTQ3MDg2MDg3Ng=="));
        list.add(new NewsContent("[搞笑]试浴室 美女恶搞 2016年11月2周", "http://player.youku.com/embed/XMTgxNTY5ODAwOA=="));
        list.add(new NewsContent("Besame_2008_C_Split_7_1080_高清", "http://player.youku.com/embed/XMTcxNzU4NTYwOA=="));
        list.add(new NewsContent("广告位。。。。", ""));
        list.add(new NewsContent("[拍客]日本性感美女写真002", "http://player.youku.com/embed/XMTgzNDg1ODUyNA=="));
        list.add(new NewsContent("[生活]性感美女", "http://player.youku.com/embed/XMTM3ODQxOTEyOA=="));
        list.add(new NewsContent("[拍客]性感美女背后摇 (114)_标清", "http://player.youku.com/embed/XMTU3OTYzNDA5Mg=="));
        list.add(new NewsContent("[拍客]臀摇", "http://player.youku.com/embed/XMTY1NTQ1NDg1Mg=="));
        list.add(new NewsContent("[拍客]美女牛仔性感紧身热舞", "http://player.youku.com/embed/XMTQzMjMyNTE5Ng=="));
        list.add(new NewsContent("[拍客]妹子身材太好，就是皮裤勒太紧了.", "http://player.youku.com/embed/XMTYzNTEyMTYyMA=="));
        list.add(new NewsContent("[生活]熊猫主播 美女 智敏儿 短裤 网丝 皮裤 舞蹈剪辑 2016 11 26", "http://player.youku.com/embed/XMTgzNzM0OTQ4OA=="));
        ListView listview = (ListView) view.findViewById(R.id.my_listview);
        myListAdapter = new MyListAdapter(list, getActivity());
        listview.setAdapter(myListAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String videoUrl = list.get(position).getUrl();
                if (isNotEmpty(videoUrl)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", videoUrl);
                    startActivity(VideoActivity.class, bundle);
                } else {
                    showToast("这是广告。。");
                }
            }
        });
    }

    @Override
    public Class<?> getClazz() {
        return BaseFragment.class;
    }
}
