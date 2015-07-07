package com.caiyun.guzhang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.myviews.MyFragmentLayout_line2;

import java.util.ArrayList;

/****资询****/
public class Fragment3 extends BaseFragment{
	public MyFragmentLayout_line2 myFragmentLayout;
	private ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.activity_transaction, container, false);
		findView();
		return view;
	}
	
	private void findView() {
		fragBaseFragments.add(new NewsFragment());
		fragBaseFragments.add(new NewsFragment());
		fragBaseFragments.add(new NewsFragment());
		fragBaseFragments.add(new NewsFragment());
		myFragmentLayout = (MyFragmentLayout_line2) view.findViewById(R.id.myFragmentLayout);
		myFragmentLayout.setScorllToNext(true);
		myFragmentLayout.setScorll(true);
		myFragmentLayout.setWhereTab(1);
		myFragmentLayout.setTabDrawble(R.drawable.shape_white_circle, (int) (6 * MainActivity.screenScale), 8);
		myFragmentLayout
				.setOnChangeFragmentListener(new MyFragmentLayout_line2.ChangeFragmentListener() {
					@Override
					public void change(int lastPosition, int positon,
							View lastTabView, View currentTabView) {
						// TODO Auto-generated method stub
						((TextView) lastTabView.findViewById(R.id.tab_text))
								.setTextColor(Fragment3.this.getResources().getColor(R.color.white_50));
						((TextView) currentTabView.findViewById(R.id.tab_text))
								.setTextColor(Fragment3.this.getResources().getColor(R.color.white));
					}
				});
		myFragmentLayout.setAdapter(fragBaseFragments, R.layout.tablayout_news,
				Cantent.VIEWPAGER_ID_NEWS);
	}
	
	public void reFreshClick(View v){
		
	}
}
