package com.caiyun.guzhang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TiCaiInfoListView_adapter;
import com.caiyun.guzhang.javabean.StockData;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/6/30.我的自选股
 */
public class ZiXuanActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private PullToRefreshListView mListView;
    private ArrayList<StockData> mList = new ArrayList();
    private TiCaiInfoListView_adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixuan);
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        setTitle(R.id.title, "自选股");
        findView();
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
        adapter = new TiCaiInfoListView_adapter(this, mList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    /**刷新按钮监听**/
    public void reFreshClick(View v){

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, StockInfoActivity.class);
        this.startActivity(intent);
    }
}
