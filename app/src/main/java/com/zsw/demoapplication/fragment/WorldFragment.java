package com.zsw.demoapplication.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.adapter.TabAdapter;
import com.zsw.demoapplication.base.BaseFragment;
import com.zsw.demoapplication.fragment.type.TabFragment2;
import com.zsw.demoapplication.http.HttpConstant;
import com.zsw.demoapplication.http.HttpManager;
import com.zsw.demoapplication.http.entity.IndexChannelResp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class WorldFragment extends BaseFragment {

    private TabLayout myTab;//tab
    private ViewPager viewPager;
    private TabAdapter tabAdapter;

    @Override
    public View bootView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_world, viewGroup, false);
    }

    @Override
    public void initBoot() {

    }

    @Override
    public void initViews() {
        myTab = findView(R.id.my_tab);
        viewPager = findView(R.id.view_page);
    }

    @Override
    public void initData(Bundle bundle) {
        httpChannel();
    }

    @Override
    public void initEvents() {

    }

    /**
     * 获取栏目名称
     */
    public void httpChannel() {

        Map<String, String> map = new HashMap<>();
        map.put("type", "1");

        HttpManager.postAsyn(HttpConstant.MENU, new HttpManager.ResultCallback<IndexChannelResp>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(IndexChannelResp response) {
//                if (response.getData() != null && response.getData().size() > 0) {
                //设置标题可以滑动，适合较多的标题。
                myTab.setTabMode(TabLayout.MODE_SCROLLABLE);
                //设置viewPager内容的集合
                List<Fragment> list_fragment = new ArrayList<>();
                List<IndexChannelResp> dataList = response.getData();
                int size = dataList.size();
                for (int i = 0; i < size; i++) {
                    TabFragment2 tabFragment2 = new TabFragment2();
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", dataList.get(i).getId());
                    tabFragment2.setArguments(bundle);
                    list_fragment.add(tabFragment2);

//                    list_fragment.add(new TabFragment2().newInstance(dataList.get(i).getSeName()));
                }
                tabAdapter = new TabAdapter(getChildFragmentManager(), list_fragment, dataList);
                viewPager.setAdapter(tabAdapter);       //将viewPager与Adapter关联
                myTab.setupWithViewPager(viewPager);     //将TabLayout和ViewPager关联起来。
//                }
            }
        }, map);
    }

    @Override
    public Class<?> getClazz() {
        return WorldFragment.class;
    }
}
