package com.zsw.demoapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zsw.demoapplication.R;
import com.zsw.demoapplication.entity.NewsContent;

import net.youmi.android.normal.banner.BannerManager;
import net.youmi.android.normal.banner.BannerViewListener;

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
        this.mInflater = LayoutInflater.from(context);
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
            if (position == list.size() / 2 || position == list.size() - 1) {
                view = mInflater.inflate(R.layout.item_ads, parent, false);
                setupBannerAd(view);

            } else {
                view = mInflater.inflate(R.layout.item_news, parent, false);
                TextView textView = (TextView) view.findViewById(R.id.news_title);
                textView.setText(list.get(position).getTitle());
            }
        }

        return view;
    }

    /**
     * 有米设置广告条广告
     */
    private void setupBannerAd(View view) {
        /**
         * 普通布局
         */
        BannerManager bannerManager = BannerManager.getInstance(context);
        MyBannerViewListener myBannerViewListener = new MyBannerViewListener();
        // 获取广告条
        View bannerView = bannerManager.getBannerView(myBannerViewListener);
        // 获取要嵌入广告条的布局
        LinearLayout bannerLayout = (LinearLayout) view.findViewById(R.id.ll_banner);

        // 将广告条加入到布局中
        bannerLayout.addView(bannerView);

        /**
         * 悬浮布局
         */
        // 实例化LayoutParams
//        FrameLayout.LayoutParams layoutParams =
//                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        // 设置广告条的悬浮位置，这里示例为右下角
//        layoutParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
//        // 获取广告条控件
//        final View bannerView =
//                BannerManager.getInstance(this).getBannerView(new BannerViewListener
//                        () {
//
//                    @Override
//                    public void onRequestSuccess() {
//                        Log.i("qqq", "请求广告条成功");
//                    }
//
//                    @Override
//                    public void onSwitchBanner() {
//                        Log.i("qqq", "广告条切换");
//                    }
//
//                    @Override
//                    public void onRequestFailed() {
//                        Log.i("qqq", "请求广告条失败");
//                    }
//                });
//        ((Activity) mContext).addContentView(bannerView, layoutParams);
    }

    class MyBannerViewListener implements BannerViewListener{
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
