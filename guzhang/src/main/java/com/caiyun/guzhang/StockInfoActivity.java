package com.caiyun.guzhang;
import android.os.Bundle;
import android.view.View;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.StockInfoTreeViewAdapter;
import com.caiyun.guzhang.view.IphoneTreeView;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.CustomProgressDialog;

import java.util.ArrayList;

public class StockInfoActivity extends BaseActivity {
	private IphoneTreeView treeView;
	private StockInfoTreeViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stock_info);
		findView();
		initTreeView();
	}
	private void findView() {
		treeView = (IphoneTreeView) findViewById(R.id.iphone_tree_view);
	}
	
	private void initTreeView() {
		 treeView.setHeaderView(View.inflate(this,
				 R.layout.tablayout_treeview_group, null));
		treeView.setGroupIndicator(null);
		adapter = new StockInfoTreeViewAdapter(this, treeView);
		treeView.setAdapter(adapter);
		adapter.expandAll();
	}
}
