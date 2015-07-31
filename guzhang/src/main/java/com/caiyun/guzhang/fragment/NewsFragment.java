package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.NewsInfoActivity;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Record_Tree;
import com.caiyun.guzhang.adapter.Fragment_news_adapter;
import com.caiyun.guzhang.adapter.TrendsActivity_adapter;
import com.caiyun.guzhang.javabean.DapanData;
import com.caiyun.guzhang.javabean.HotData;
import com.caiyun.guzhang.javabean.NewsData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * **资询***
 */
public class NewsFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    View view;
    private PullToRefreshListView mListView;
    private int page = 0;
    private ArrayList<NewsData> mList = new ArrayList();
    private Fragment_news_adapter adapter;
    private View loading,noData;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = inflater.inflate(R.layout.fragment_news, container, false);
        findView();
        getData(0);
        return view;
    }

    private void findView() {
        loading = view.findViewById(R.id.list_loading_cover);
        noData = view.findViewById(R.id.list_result_cover);
        loading.setVisibility(View.VISIBLE);
        mListView = (PullToRefreshListView) view.findViewById(R.id.mListView);
        mListView.setOnItemClickListener(this);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(
                "正在刷新");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                "正在加载");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载");
        mListView.setOnRefreshListener(new com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase refreshView) {
                refreshView.setRefreshing(true);
                page = 0;
                getData(0);
            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase refreshView) {
                refreshView.setRefreshing(true);
                page += 1;
                getData(1);
            }
        });
        adapter = new Fragment_news_adapter(context, mList);
        mListView.setAdapter(adapter);
    }

    /**
     * 获取内参数据**
     *
     * @param type 0为下拉刷新，1为上拉加载
     */
    public void getData(final int type) {
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Neican.getList", new String[]{"page", "pagesize"}, new String[]{String.valueOf(page), "10"}), null, new VolleyListerner(context) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                loading.setVisibility(View.GONE);
                if (type == 0) {
                    mList.clear();
                }
                JsonUtils.listFromJsonWithSubKey(response.getString("data"), NewsData.class, "neicanlist", mList);
                if (mList.isEmpty()) {
                    noData.setVisibility(View.VISIBLE);
                } else {
                    noData.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
            }
        }, new VolleyErrorListoner(context){
            @Override
            public void onError(VolleyError error) {
                loading.setVisibility(View.GONE);
                mListView.onRefreshComplete();
            }
        });
        MainActivity.mQueue.add(request);
    }

    /**
     * 每次显示时调用的方法
     */
    public void onShow(int lastPosition) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(context, NewsInfoActivity.class);
        intent.putExtra("id", mList.get(position-1).getId());
        this.startActivity(intent);
    }
}
