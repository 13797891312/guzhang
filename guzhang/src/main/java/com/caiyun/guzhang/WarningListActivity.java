package com.caiyun.guzhang;

import android.os.Bundle;
import android.widget.ListView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.WarningListView_adapter;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/6/30.智能预警
 */
public class WarningListActivity extends BaseActivity{
    private ListView mListView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warninglist);
        findView();
    }

    private void findView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(new WarningListView_adapter(this,null));
    }
}
