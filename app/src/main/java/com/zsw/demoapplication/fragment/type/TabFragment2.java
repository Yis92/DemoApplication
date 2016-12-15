package com.zsw.demoapplication.fragment.type;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.squareup.okhttp.Request;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.world.VideoActivity;
import com.zsw.demoapplication.adapter.MyListAdapter;
import com.zsw.demoapplication.base.BaseFragment;
import com.zsw.demoapplication.entity.NewsContent;
import com.zsw.demoapplication.http.HttpConstant;
import com.zsw.demoapplication.http.HttpManager;
import com.zsw.demoapplication.http.entity.IndexVideoTitleResp;
import com.zsw.demoapplication.widget.SwipeRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class TabFragment2 extends BaseFragment {

    private String title;

    private SwipeRefreshView mRefreshLayout;
    private LinkedList<IndexVideoTitleResp> list = new LinkedList();
    private MyListAdapter myListAdapter;
    private ListView listview;
    private int type;
    private static final int FIRST = 100;
    private static final int REFRESH = 101;
    private static final int LOAD = 102;


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
        mRefreshLayout = findView(R.id.srl_refresh);
        listview = findView(R.id.my_listview);
//        //设置下拉进度的背景颜色，默认就是白色的
//        mRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.white));
//        //设置下拉进度的主题颜色
//        mRefreshLayout.setColorSchemeColors(
//                getResources().getColor(android.R.color.holo_blue_bright),
//                getResources().getColor(android.R.color.holo_green_light),
//                getResources().getColor(android.R.color.holo_orange_light),
//                getResources().getColor(android.R.color.holo_red_light));

    }

    @Override
    public void initData(Bundle bundle) {

        //获取导航栏的参数
        bundle = getArguments();
        type = bundle.getInt("type");
        httpVideoTitle(type, FIRST);

//        String url = "";
//        if ("华语".equals(title)) {
//            //传递华语的请求参数，访问后台接口
//            initListData();
//        } else if ("欧美".equals(title)) {
//            //传递欧美的请求参数，访问后台接口
//            initListData();
//        } else if ("日语".equals(title)) {
//            //传递日语的请求参数，访问后台接口
//            initListData();
//        } else if ("韩语".equals(title)) {
//            //传递韩语的请求参数，访问后台接口
//            initListData();
//        } else {
//            initListData();
//        }
    }

    @Override
    public void initEvents() {
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                //swipeRefreshLayout.setRefreshing(true);
                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                httpVideoTitle(type, REFRESH);
            }
        });
        // 设置上拉加载更多
        mRefreshLayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
            @Override
            public void onLoad() {
                httpVideoTitle(type, LOAD);
            }
        });
    }

//    private void initWebData(String url) {
//        WebView webview = (WebView) view.findViewById(R.id.my_webview);
//        final ProgressBar bar = (ProgressBar) view.findViewById(R.id.myProgressBar);
//        WebSettings webset = webview.getSettings();
//        //设置WebView属性，能够执行Javascript脚本
//        webset.setJavaScriptEnabled(true);
//        // 设置可以支持缩放
//        webset.setSupportZoom(true);
//        // 设置出现缩放工具
//        webset.setBuiltInZoomControls(true);
//        //不显示缩小镜和放大镜按钮
//        webset.setDisplayZoomControls(false);
//        //扩大比例的缩放
//        webset.setUseWideViewPort(true);
//        //自适应屏幕
//        webset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        webset.setLoadWithOverviewMode(true);
//        //当页面中点击链接时，会调用这个方法
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
//        //显示进度条
//        webview.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                if (newProgress == 100) {
//                    bar.setVisibility(View.GONE);
//                } else {
//                    if (View.GONE == bar.getVisibility()) {
//                        bar.setVisibility(View.VISIBLE);
//                    }
//                    bar.setProgress(newProgress);
//                }
//                super.onProgressChanged(view, newProgress);
//            }
//
//        });
//        //加载需要显示的网页
//        webview.loadUrl(url);
//    }

//    private void initListData() {
//        list.clear();
//        list.add(new NewsContent("[生活]苍井空", "http://player.youku.com/embed/XMzkyODEyMzY4"));
//        list.add(new NewsContent("[娱乐]美国众女星卷入艳照门 詹妮弗-劳伦斯60张裸照外泄", "http://player.youku.com/embed/XNzY3NDE2MDIw"));
//        list.add(new NewsContent("[音乐]Trap Mix 2016 [ Best of Trap Music ]", "http://player.youku.com/embed/XMTQ3MDg2MDg3Ng=="));
//        list.add(new NewsContent("[搞笑]试浴室 美女恶搞 2016年11月2周", "http://player.youku.com/embed/XMTgxNTY5ODAwOA=="));
//        list.add(new NewsContent("Besame_2008_C_Split_7_1080_高清", "http://player.youku.com/embed/XMTcxNzU4NTYwOA=="));
//        list.add(new NewsContent("广告位。。。。", ""));
//        list.add(new NewsContent("[拍客]日本性感美女写真002", "http://player.youku.com/embed/XMTgzNDg1ODUyNA=="));
//        list.add(new NewsContent("[生活]性感美女", "http://player.youku.com/embed/XMTM3ODQxOTEyOA=="));
//        list.add(new NewsContent("[拍客]性感美女背后摇 (114)_标清", "http://player.youku.com/embed/XMTU3OTYzNDA5Mg=="));
//        list.add(new NewsContent("[拍客]臀摇", "http://player.youku.com/embed/XMTY1NTQ1NDg1Mg=="));
//        list.add(new NewsContent("[拍客]美女牛仔性感紧身热舞", "http://player.youku.com/embed/XMTQzMjMyNTE5Ng=="));
//        list.add(new NewsContent("[拍客]妹子身材太好，就是皮裤勒太紧了.", "http://player.youku.com/embed/XMTYzNTEyMTYyMA=="));
//        list.add(new NewsContent("[生活]熊猫主播 美女 智敏儿 短裤 网丝 皮裤 舞蹈剪辑 2016 11 26", "http://player.youku.com/embed/XMTgzNzM0OTQ4OA=="));
//
//        myListAdapter = new MyListAdapter(list, getActivity());
//        listview.setAdapter(myListAdapter);
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String videoUrl = list.get(position).getUrl();
//                if (isNotEmpty(videoUrl)) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("url", videoUrl);
//                    startActivity(VideoActivity.class, bundle);
//                } else {
//                    showToast("这是广告。。");
//                }
//            }
//        });
//    }

    /**
     * 获取视频的标题信息
     *
     * @param type 栏目ID
     */
    public void httpVideoTitle(int type, final int requestType) {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", type + "");

        HttpManager.postAsyn(HttpConstant.VIDEO_TITLE, new HttpManager.ResultCallback<IndexVideoTitleResp>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(final IndexVideoTitleResp response) {
                if (response.getCode() == 0) {
                    switch (requestType) {
                        case FIRST:
                            list.addAll(response.getData());
                            myListAdapter = new MyListAdapter(getActivity(), list);
                            listview.setAdapter(myListAdapter);
                            break;
                        case REFRESH:
                            list.addAll(0, response.getData());
                            myListAdapter.notifyDataSetChanged();
                            mRefreshLayout.setRefreshing(false);
                            break;
                        case LOAD:
                            list.addAll(response.getData());
                            myListAdapter.notifyDataSetChanged();
                            mRefreshLayout.setLoading(false);
                            break;
                    }

                }
            }
        }, map);
    }


    @Override
    public Class<?> getClazz() {
        return BaseFragment.class;
    }
}
