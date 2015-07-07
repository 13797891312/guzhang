package com.caiyun.guzhang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.MyStockFragment_has;
import com.caiyun.guzhang.fragment.MyStockFragment_record;
import com.caiyun.guzhang.fragment.TransactionFragment_cancel;
import com.caiyun.guzhang.fragment.TransactionFragment_transaction;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.MyFragmentLayout_line2;

import java.util.ArrayList;

public class MyStockActivity extends BaseActivity{
	public MyFragmentLayout_line2 myFragmentLayout;
	private ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		findView();
	}
	
	private void findView() {
		fragBaseFragments.add(new MyStockFragment_has());
		fragBaseFragments.add(new MyStockFragment_record());
		myFragmentLayout = (MyFragmentLayout_line2)findViewById(R.id.myFragmentLayout);
		myFragmentLayout.setScorllToNext(true);
		myFragmentLayout.setScorll(true);
		myFragmentLayout.setWhereTab(1);
		myFragmentLayout.setTabDrawble(R.drawable.shape_white_circle, (int) (6 * MainActivity.screenScale), 4);
		myFragmentLayout
				.setOnChangeFragmentListener(new MyFragmentLayout_line2.ChangeFragmentListener() {
					@Override
					public void change(int lastPosition, int positon,
							View lastTabView, View currentTabView) {
						// TODO Auto-generated method stub
						((TextView) lastTabView.findViewById(R.id.tab_text))
								.setTextColor(MyStockActivity.this.getResources().getColor(R.color.white_50));
						((TextView) currentTabView.findViewById(R.id.tab_text))
								.setTextColor(MyStockActivity.this.getResources().getColor(R.color.white));
					}
				});
		myFragmentLayout.setAdapter(fragBaseFragments, R.layout.tablayout_mystock,
				Cantent.VIEWPAGER_ID_MYSTOCK);
	}
	
	public void reFreshClick(View v){
		
	}
}
