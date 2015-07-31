package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TiCaiInfoListView_adapter;
import com.caiyun.guzhang.javabean.NewsData;
import com.caiyun.guzhang.javabean.StockData;
import com.caiyun.guzhang.util.JsonUtils;
import com.caiyun.guzhang.util.UrlUtils;
import com.caiyun.guzhang.util.VolleyErrorListoner;
import com.caiyun.guzhang.util.VolleyListerner;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/6/30.相关股票
 */
public class AboutStockActivity extends BaseActivity{
    private PullToRefreshListView mListView;
    private ArrayList<StockData> mList = new ArrayList();
    private TiCaiInfoListView_adapter adapter;
    private RequestQueue mQueue;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutstock);
        setTitle(R.id.title, "相关股票");
        mQueue = Volley.newRequestQueue(this);
        id = getIntent().getStringExtra("id");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
        getData();
    }

    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(
                "正在刷新");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                "正在加载");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载");
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                refreshView.setRefreshing(true);
                getData();
            }
        });
        adapter = new TiCaiInfoListView_adapter(this, mList);
        mListView.setAdapter(adapter);
    }

    /**
     * 获取股票列表**
     *
     *
     */
    public void getData() {
        mListView.setRefreshing(true);
        JsonObjectRequest request = new JsonObjectRequest(UrlUtils.getHttpUrl("Neican.getRelStocks", new String[]{"id"}, new String[]{id}), null, new VolleyListerner(this) {
            @Override
            public void onSucess(JSONObject response) throws Exception {
                super.onSucess(response);
                mList.clear();
                JsonUtils.listFromJsonWithSubKey(response.toString(), StockData.class, "data", mList);
                adapter.notifyDataSetChanged();
                mListView.onRefreshComplete();
                if (mList.isEmpty()) {
                    mListView.setVisibility(View.GONE);
                    findViewById(R.id.list_result_cover).setVisibility(View.VISIBLE);
                }
            }
        }, new VolleyErrorListoner(this) {
            @Override
            public void onError(VolleyError error) {
                mListView.onRefreshComplete();
            }
        });

        MainActivity.mQueue.add(request);
    }
}
