package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_MyStock_Has_Listview;
import com.caiyun.guzhang.adapter.Fragment_transaction_cancel_listview;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MyStockFragment_has extends BaseFragment{
	View view;
	Context context;
	private PullToRefreshListView mListView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_transaction_cancel, container, false);
		findView();
		return view;
	}
	private void findView() {
		mListView=(PullToRefreshListView) view.findViewById(R.id.mListView);
		mListView.setAdapter(new Fragment_MyStock_Has_Listview(this.getActivity(), null));
		mListView.getRefreshableView().setDivider(new ColorDrawable(getResources().getColor(R.color.backgroud)));
		mListView.getRefreshableView().setDividerHeight(24);
		mListView.getLoadingLayoutProxy().setPullLabel("下拉刷新");
		mListView.getLoadingLayoutProxy().setRefreshingLabel("正在加载");
		mListView.getLoadingLayoutProxy().setReleaseLabel("松开刷新");
	}
	/**
	 * 每次显示时调用的方法
	 */
	public void onShow(int lastPosition) {

	}
}
