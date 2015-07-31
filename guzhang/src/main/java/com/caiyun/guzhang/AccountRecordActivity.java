package com.caiyun.guzhang;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.AccountRecord_TreeAdapter;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Record_Tree;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.zhaojin.activity.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2015/7/10.
 */
public class AccountRecordActivity extends BaseActivity {
    private IphoneTreeView treeView;
    private AccountRecord_TreeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountrecord);
        setTitle(R.id.title, "钱包明细");
        findViewById(R.id.reFresh_layout).setVisibility(View.GONE);
        initTreeView();

    }

    private void initTreeView() {
        treeView = (IphoneTreeView) findViewById(R.id.iphone_tree_view);
        treeView.setHeaderView(View.inflate(this,
                R.layout.header_tree_contact, null));
        treeView.setGroupIndicator(null);
        adapter = new AccountRecord_TreeAdapter(this, treeView, new ArrayList(), new HashMap());
        treeView.setAdapter(adapter);
        adapter.expandAll();
        treeView.setLoadMoreListener(new IphoneTreeView.LoadMoreListener() {
            @Override
            public void LoadMore() {
                Toast.makeText(AccountRecordActivity.this, "加载更多", Toast.LENGTH_LONG).show();
                treeView.loadMoreFinish();
            }
        });
    }
}
