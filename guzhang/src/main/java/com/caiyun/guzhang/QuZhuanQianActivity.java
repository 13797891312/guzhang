package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_Notice_GuanZhu_Listview;
import com.caiyun.guzhang.adapter.QuZhuanQianActivity_adapter;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.activity.BaseActivity;

/**
 * Created by Administrator on 2015/7/10. 去赚钱跳转到跟买的页面，
 */
public class QuZhuanQianActivity extends BaseActivity {
    PullToRefreshListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quzhuangqian);
        setTitle(R.id.title, "跟买");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        findView();
    }


    private void findView() {
        mListView = (PullToRefreshListView) findViewById(R.id.mListView);
        mListView.setAdapter(new QuZhuanQianActivity_adapter(this, null));
        mListView.setDividerPadding(0);
        mListView.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新");
        mListView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
        mListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(
                "正在刷新");
        mListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(
                "正在加载");
        mListView.getLoadingLayoutProxy(true, false).setReleaseLabel("松开刷新");
        mListView.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载");
    }
}
