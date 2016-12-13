package com.zsw.demoapplication.adapter;

import android.content.Context;
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
import com.zsw.demoapplication.entity.NewsContent;

import org.json.JSONObject;

import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class ResourceListAdapter extends BaseAdapter {
    private List<NewsContent> list;
    private LayoutInflater mInflater;
    private Context context;

    public ResourceListAdapter(List<NewsContent> list, Context context) {
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
            view = mInflater.inflate(R.layout.item_resource, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.news_title);
        textView.setText(list.get(position).getTitle());
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ads);
        initAds(linearLayout);
        return view;
    }
    private void initAds(LinearLayout view) {
        //人群属性
        AdSettings.setKey(new String[]{"baidu", "中国"});
        //创建广告view
        String adPlaceID = "3107650";//重要：请填上你的代码位ID,否则无法请求到广告
        AdView adView= new AdView(context, adPlaceID);
        // 设置'广告着陆页'颜色主题
        AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_WHITE_THEME);
        // 另外，也可设置劢作栏中单个元素的颜色, 颜色参数为四段制，0xFF(透明度, 一般填FF)DE(红) DA(绿) DB(蓝)
        AppActivity.getActionBarColorTheme().setBackgroundColor(
                0xFFDEDADB);

        //设置监听器
        adView.setListener(new AdViewListener() {
            @Override
            public void onAdReady(AdView adView) {

            }

            @Override
            public void onAdShow(JSONObject jsonObject) {

            }

            @Override
            public void onAdClick(JSONObject jsonObject) {

            }

            @Override
            public void onAdFailed(String s) {

            }

            @Override
            public void onAdSwitch() {

            }

            @Override
            public void onAdClose(JSONObject jsonObject) {

            }

        });
        //将adView添加到父控件中（注：该父控件丌一定为您癿根控件，只要该控件能通过addView添加广告视图即可）
        RelativeLayout.LayoutParams rllp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rllp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        view.addView(adView, rllp);
    }
}
