package com.caiyun.guzhang.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.RankFragmentAdapter_shouyi;
import com.caiyun.guzhang.util.Cantent;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhaojin.myviews.MyFragmentLayout_line;

import java.util.ArrayList;

public class RankFragment_Root extends BaseFragment{
	View view;
	Context context;
	public MyFragmentLayout_line myFragmentLayout;
	public ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_rank, container, false);
		findView();
		return view;
	}
	private void findView() {
		fragBaseFragments.add(new RankFragment());
		fragBaseFragments.add(new RankFragment());
		fragBaseFragments.add(new RankFragment());
		myFragmentLayout = (MyFragmentLayout_line) view.findViewById(R.id.myFragmentLayout);
		myFragmentLayout.setScorllToNext(true);
		myFragmentLayout.setScorll(true);
		myFragmentLayout.setWhereTab(1);
		myFragmentLayout.setTabDrawble(R.drawable.arrow_tab_line, 20);
		myFragmentLayout
				.setOnChangeFragmentListener(new MyFragmentLayout_line.ChangeFragmentListener() {
					@Override
					public void change(int lastPosition, int positon,
									   View lastTabView, View currentTabView) {
						// TODO Auto-generated method stub
						((BaseFragment) fragBaseFragments.get(positon))
								.onShow(lastPosition);
					}
				});
		myFragmentLayout.setAdapter(fragBaseFragments, R.layout.tablayout_rank,
				Cantent.VIEWPAGER_ID_RANK);
		myFragmentLayout.setCurrenItem(0);
	}
	/**
	 * 每次显示时调用的方法
	 */
	public void onShow(int lastPosition) {

	}
}
