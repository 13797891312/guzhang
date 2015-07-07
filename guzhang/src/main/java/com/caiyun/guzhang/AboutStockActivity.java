package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TiCaiInfoListView_adapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/6/30.相关股票
 */
public class AboutStockActivity extends BaseActivity{
    private PullToRefreshListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutstock);
        setTitle(R.id.title, "水利建设");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
    }

    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setAdapter(new TiCaiInfoListView_adapter(this, null));
    }
}
