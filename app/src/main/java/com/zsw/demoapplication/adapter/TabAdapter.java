package com.zsw.demoapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zsw.demoapplication.http.entity.IndexChannelResp;

import java.util.List;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */
public class TabAdapter extends FragmentPagerAdapter{
    private List<Fragment> list;
    private List<IndexChannelResp> titile_list;

    public TabAdapter(FragmentManager fm, List<Fragment> list, List<IndexChannelResp> titile_list) {
        super(fm);
        this.list = list;
        this.titile_list = titile_list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    //添加页卡的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titile_list.get(position).getSeName();
    }
}
