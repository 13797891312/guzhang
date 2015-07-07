package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.MyFriendsAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/6. 股友列表
 */
public class MyFriendsActivity extends BaseActivity {
    private PullToRefreshListView mListView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfriends);
        setTitle(R.id.title, "股友列表");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
    }

    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setAdapter(new MyFriendsAdapter(this,null));

    }
}
