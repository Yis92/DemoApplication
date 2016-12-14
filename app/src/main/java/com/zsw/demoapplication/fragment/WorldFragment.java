package com.zsw.demoapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.adapter.TabAdapter;
import com.zsw.demoapplication.fragment.type.TabFragment2;
import com.zsw.demoapplication.http.HttpConstant;
import com.zsw.demoapplication.http.HttpManager;
import com.zsw.demoapplication.http.entity.IndexChannelResp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class WorldFragment extends Fragment {

    private static final String TAG = "qqq";
    private TabLayout myTab;        //tab
    private List<String> list_title;           //tab名称列表
    private ViewPager viewPager;
    private TabAdapter tabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_world, null);
        initViews(view);
        initData();
        return view;

    }

    private void initViews(View view) {
        myTab = (TabLayout) view.findViewById(R.id.my_tab);
        viewPager = (ViewPager) view.findViewById(R.id.view_page);
    }

    private void initData() {

        httpChannel();
//        //设置标题集合
//        list_title = new ArrayList<>();
//        list_title.add("首页");
//        list_title.add("华语");
//        list_title.add("欧美");
//        list_title.add("日语");
//        list_title.add("韩语");
//        int size = list_title.size();
//        //设置标题可以滑动，适合较多的标题。
//        myTab.setTabMode(TabLayout.MODE_FIXED);
//        //设置viewPager内容的集合
//        List<Fragment> list_fragment = new ArrayList<>();
//        for(int i = 0;i<size;i++){
//            list_fragment.add(new TabFragment2().newInstance(list_title.get(i)));
//        }
//        tabAdapter = new TabAdapter(getChildFragmentManager(),list_fragment,list_title);
//        viewPager.setAdapter(tabAdapter);       //将viewPager与Adapter关联
//        myTab.setupWithViewPager(viewPager);     //将TabLayout和ViewPager关联起来。
    }

    /**
     * 获取栏目名称
     */
    public void httpChannel(){

        HttpManager.getAsyn(HttpConstant.MENU, new HttpManager.ResultCallback<IndexChannelResp>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(IndexChannelResp response) {
                //设置标题可以滑动，适合较多的标题。
                myTab.setTabMode(TabLayout.MODE_FIXED);
                //设置viewPager内容的集合
                List<Fragment> list_fragment = new ArrayList<>();
                List<IndexChannelResp> dataList = response.getData();
                int size = dataList.size();
                for(int i = 0;i<size;i++){
                    TabFragment2 tabFragment2 = new TabFragment2();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", dataList.get(i).getId());
                    tabFragment2.setArguments(bundle);
                    list_fragment.add(tabFragment2);


//                    list_fragment.add(new TabFragment2().newInstance(dataList.get(i).getSeName()));
                }
                tabAdapter = new TabAdapter(getChildFragmentManager(),list_fragment,dataList);
                viewPager.setAdapter(tabAdapter);       //将viewPager与Adapter关联
                myTab.setupWithViewPager(viewPager);     //将TabLayout和ViewPager关联起来。
            }
        });
    }

}
