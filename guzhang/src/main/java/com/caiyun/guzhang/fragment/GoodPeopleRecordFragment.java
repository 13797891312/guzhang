package com.caiyun.guzhang.fragment;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment_transaction_cancel_listview;
import com.caiyun.guzhang.view.ListViewForScollView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GoodPeopleRecordFragment extends BaseFragment{
	View view;
	Context context;
	private ListViewForScollView mListView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_goodpeople_record, container, false);
		findView();
		return view;
	}
	private void findView() {
		mListView=(ListViewForScollView) view.findViewById(R.id.mListView);
		mListView.setAdapter(new Fragment_transaction_cancel_listview(this.getActivity(), null));
	}
}
