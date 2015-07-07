package com.caiyun.guzhang.fragment;



import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.adapter.Fragment2_gridView_adapter;
import com.caiyun.guzhang.view.GridViewForScollView;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends BaseFragment  {
	private Context context;
	private View view;
	private GridViewForScollView mGridView;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_1, container, false);
		findView();
		return view;
	}

	private void findView() {
		mGridView=(GridViewForScollView) view.findViewById(R.id.gridview);
		mGridView.setAdapter(new Fragment2_gridView_adapter(context,null));
	}

	@Override
	public void onShow(int lastPosition) {
	};
	
}
