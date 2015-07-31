package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.RankFragmentAdapter_shouyi;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class RankFragment extends BaseFragment{
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
		view = inflater.inflate(R.layout.fragment_rank_shouyi, container, false);
		findView();
		return view;
	}
	private void findView() {
		mListView=(PullToRefreshListView) view.findViewById(R.id.mListView);
		mListView.setAdapter(new RankFragmentAdapter_shouyi(this.getActivity(), null));
	}
	/**
	 * 每次显示时调用的方法
	 */
	public void onShow(int lastPosition) {

	}
}
