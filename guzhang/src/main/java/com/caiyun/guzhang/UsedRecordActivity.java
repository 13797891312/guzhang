package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.TrendsActivity_adapter;
import com.caiyun.guzhang.adapter.UsedRecordActivity_adapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/3. 消费记录
 */
public class UsedRecordActivity extends BaseActivity{
    private PullToRefreshListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usedrecord);
        setTitle(R.id.title, "消费记录");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
    }
    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setAdapter(new UsedRecordActivity_adapter(this, null));
    }
}
