package com.zsw.demoapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.utils.L;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.world.VideoActivity;
import com.zsw.demoapplication.entity.NewsContent;
import com.zsw.demoapplication.http.entity.IndexVideoTitleResp;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

import java.util.List;

import cn.trinea.android.common.adapter.CommonAdapter;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class MyListAdapter extends CommonAdapter<IndexVideoTitleResp> {

    private TextView tvTitle;
    private LinearLayout llContent;

    public MyListAdapter(Activity activity, List<IndexVideoTitleResp> list) {
        super(activity, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_news, parent, false);
        }

        tvTitle = get(convertView, R.id.news_title);
        llContent = get(convertView, R.id.ll_content);
        if (list.get(position)==null) {
            //表示这里插入一条广告
            llContent.setBackgroundColor(getRes().getColor(R.color.red));
//            setupBannerAd(llContent);
        }else{
            tvTitle.setVisibility(View.VISIBLE);
            String title = list.get(position).getCoTitle();
            tvTitle.setText(title);
        }


        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position) != null && isNotEmpty(list.get(position).getCoUrl())) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", list.get(position).getCoUrl());
                    startActivity(VideoActivity.class, bundle);
                } else {
                    showToast("这是广告。。");
                }
            }
        });
        return convertView;
    }

    /**
     * 有米设置广告条广告
     */
    private void setupBannerAd(LinearLayout llContent) {
        /**
         * 普通布局
         */
        BannerManager bannerManager = BannerManager.getInstance(activity);
        MyBannerViewListener myBannerViewListener = new MyBannerViewListener();
        // 获取广告条
        View bannerView = bannerManager.getBannerView(myBannerViewListener);
        // 将广告条加入到布局中
        llContent.addView(bannerView);

    }

    class MyBannerViewListener implements BannerViewListener {
        @Override
        public void onRequestSuccess() {
            Log.d("qqq", "请求广告条成功");
        }

        @Override
        public void onSwitchBanner() {
            Log.d("qqq", "广告条切换");
        }

        @Override
        public void onRequestFailed() {
            Log.d("qqq", "请求广告条失败");
        }
    }
}
