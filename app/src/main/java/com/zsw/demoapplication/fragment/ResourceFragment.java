package com.zsw.demoapplication.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.zsw.demoapplication.R;
import com.zsw.demoapplication.adapter.MyListAdapter;
import com.zsw.demoapplication.adapter.ResourceListAdapter;
import com.zsw.demoapplication.base.BaseFragment;
import com.zsw.demoapplication.http.HttpConstant;
import com.zsw.demoapplication.http.HttpManager;
import com.zsw.demoapplication.http.entity.IndexVideoTitleResp;
import com.zsw.demoapplication.widget.SuperRefreshLayout;
import com.zsw.demoapplication.widget.SwipeRefreshView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author zeng
 * @date 2016/12/9
 * @Description:
 */

public class ResourceFragment extends BaseFragment {

    private SuperRefreshLayout mRefreshLayout;
    private LinkedList<IndexVideoTitleResp> list = new LinkedList();
    private ResourceListAdapter myListAdapter;
    private ListView listview;
    private int type;
    private static final int FIRST = 100;
    private static final int REFRESH = 101;
    private static final int LOAD = 102;
    private final int pageSize = 15;//20条数据一页
    private int pageNo = 1;//第几页（页码）
    //    private SwipeRefreshView mRefreshLayout;
//    private final List<NewsContent> list = new ArrayList<>();
//    private ResourceListAdapter resourceListAdapter;
//    private ListView listview ;
    private TextView title;

    @Override
    public View bootView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_resource, viewGroup, false);
    }

    @Override
    public void initBoot() {

    }

    @Override
    public void initViews() {
        mRefreshLayout = findView(R.id.srl_refresh);
        listview = findView(R.id.my_listview);
        title = findView(R.id.tv_title);
    }

    @Override
    public void initData(Bundle bundle) {
        title.setText(getString(R.string.resource_title));

        mRefreshLayout.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);


        httpVideoTitle(FIRST);
//        initListData();
    }

    @Override
    public void initEvents() {
        mRefreshLayout.setSuperRefreshLayoutListener(new SuperRefreshLayout.SuperRefreshLayoutListener() {
            @Override
            public void onRefreshing() {
                // 开始刷新，设置当前为刷新状态
                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                // TODO 获取数据
                pageNo = 1;
                httpVideoTitle(REFRESH);
            }

            @Override
            public void onLoadMore() {
                showToast("1111");
                pageNo++;
                httpVideoTitle(LOAD);
            }
        });
        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
//        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                // 开始刷新，设置当前为刷新状态
//                // 这里是主线程
//                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
//                // TODO 获取数据
//                pageNo = 1;
//                httpVideoTitle(REFRESH);
//            }
//        });
//        // 设置上拉加载更多
//        mRefreshLayout.setOnLoadListener(new SwipeRefreshView.OnLoadListener() {
//            @Override
//            public void onLoad() {
//                pageNo++;
//                httpVideoTitle(LOAD);
//            }
//        });

    }
//    private void initListData() {
//        list.clear();
//        list.add(new NewsContent("资源页", "http://player.youku.com/embed/XMzkyODEyMzY4"));
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
//        resourceListAdapter = new ResourceListAdapter(list, getActivity());
//        listview.setAdapter(resourceListAdapter);
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

    /**
     * 获取视频的标题信息
     */
    public void httpVideoTitle(final int requestType) {

        HashMap<String, String> map = new HashMap<>();
        map.put("type", "6");
        map.put("pageNo", pageNo + "");
        map.put("pageSize", pageSize + "");

        HttpManager.postAsyn(HttpConstant.VIDEO_TITLE, new HttpManager.ResultCallback<IndexVideoTitleResp>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(final IndexVideoTitleResp response) {
                if (response.getCode() == 0) {

                    if (response.getData() != null && response.getData().size() > 0) {
                        switch (requestType) {
                            case FIRST:
                                list.clear();
                                list.addAll(response.getData());
                                myListAdapter = new ResourceListAdapter(getActivity(), list);
                                listview.setAdapter(myListAdapter);

                                if (response.getData().size() >= pageSize) {
                                    mRefreshLayout.setCanLoadMore(listview);//是否可以加载更多
                                }
                                mRefreshLayout.onLoadComplete();

                                break;
                            case REFRESH:
                                list.clear();
                                list.addAll(response.getData());
                                myListAdapter.notifyDataSetChanged();
//                                mRefreshLayout.setRefreshing(false);
                                mRefreshLayout.onLoadComplete();
                                break;
                            case LOAD:
                                Log.e("qqq", pageNo + "");
                                if (response.getData() == null || response.getData().size() == 0) {
                                    Toast.makeText(activity, "没有更多啦", Toast.LENGTH_SHORT).show();
                                } else {
                                    list.addAll(response.getData());
                                    myListAdapter.notifyDataSetChanged();
                                }
//                                mRefreshLayout.setLoading(false);
                                mRefreshLayout.onLoadComplete();
                                break;
                        }
                    } else {
//                        if (pageNo > 1) {
//                            mRefreshLayout.setFooterType(4);
//                        }
//                        mRefreshLayout.setRefreshing(false);
                        mRefreshLayout.onLoadComplete();
                    }

                }
            }
        }, map);
    }

    @Override
    public Class<?> getClazz() {
        return ResourceFragment.class;
    }
}
