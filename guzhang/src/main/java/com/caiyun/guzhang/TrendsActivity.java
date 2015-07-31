package com.caiyun.guzhang;

import android.os.Bundle;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TrendsActivity_adapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/6/30.动态，交易记录
 */
public class TrendsActivity extends BaseActivity{
    private PullToRefreshListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trends);
        findView();
    }

    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setAdapter(new TrendsActivity_adapter(this,null));
    }
}
