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

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.activity.world.VideoActivity;
import com.zsw.demoapplication.entity.NewsContent;
import com.zsw.demoapplication.http.entity.IndexVideoTitleResp;

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

        tvTitle.setText(list.get(position).getCoTitle());

        llContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoUrl = list.get(position).getCoUrl();
                if (isNotEmpty(videoUrl)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("url", videoUrl);
                    startActivity(VideoActivity.class, bundle);
                } else {
                    showToast("这是广告。。");
                }
            }
        });
        return convertView;
    }
}
