package com.zsw.demoapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.entity.NewsContent;

import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class MyListAdapter extends BaseAdapter {
    private List<NewsContent> list;
    private LayoutInflater mInflater;
    private Context context;

    public MyListAdapter(List<NewsContent> list, Context context) {
        this.list = list;
        this.context = context;
        this.mInflater =LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(R.layout.item_news, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        textView.setText(list.get(position).getTitle());
        return view;
    }
}
