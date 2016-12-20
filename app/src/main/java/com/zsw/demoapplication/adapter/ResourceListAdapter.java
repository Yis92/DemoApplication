package com.zsw.demoapplication.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.baidu.mobads.AppActivity;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.world.ResourceDetailActivity;
import com.zsw.demoapplication.activity.world.VideoActivity;
import com.zsw.demoapplication.constant.Constant;
import com.zsw.demoapplication.entity.NewsContent;
import com.zsw.demoapplication.http.entity.IndexVideoTitleResp;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

import org.json.JSONObject;

import java.util.List;

import cn.trinea.android.common.adapter.CommonAdapter;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class ResourceListAdapter extends CommonAdapter<IndexVideoTitleResp> {

    private TextView tvTitle;
    private LinearLayout llContent;

    public ResourceListAdapter(Activity activity, List<IndexVideoTitleResp> list) {
        super(activity, list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_news, parent, false);
        }

        tvTitle = get(convertView, R.id.news_title);
        llContent = get(convertView, R.id.ll_content);
        if (list.get(position) == null) {
            //表示这里插入一条广告
            llContent.setBackgroundColor(getRes().getColor(R.color.red));
//            setupBannerAd(llContent);
        } else {
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
                    startActivity(ResourceDetailActivity.class, bundle);
                } else {
//                    showToast("这是广告。。");
                }
            }
        });
        return convertView;
    }
}
