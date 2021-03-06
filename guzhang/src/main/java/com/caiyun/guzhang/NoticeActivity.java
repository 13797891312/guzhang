package com.caiyun.guzhang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.fragment.MyStockFragment_has;
import com.caiyun.guzhang.fragment.MyStockFragment_record;
import com.caiyun.guzhang.fragment.NoticeFragment_guanzhu;
import com.caiyun.guzhang.fragment.NoticeFragment_notice;
import com.caiyun.guzhang.fragment.NoticeFragment_sixing;
import com.caiyun.guzhang.util.Cantent;
import com.zhaojin.activity.BaseActivity;
import com.zhaojin.myviews.MyFragmentLayout_line2;

import java.util.ArrayList;

/***通知***/
public class NoticeActivity extends BaseActivity{
	public MyFragmentLayout_line2 myFragmentLayout;
	private ArrayList<Fragment> fragBaseFragments = new ArrayList<Fragment>();
	private TextView textView_config;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		findView();
	}
	
	private void findView() {
		fragBaseFragments.add(new NoticeFragment_sixing());
		fragBaseFragments.add(new NoticeFragment_guanzhu());
		fragBaseFragments.add(new NoticeFragment_notice());
		myFragmentLayout = (MyFragmentLayout_line2) findViewById(R.id.myFragmentLayout);
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
								.setTextColor(NoticeActivity.this.getResources().getColor(R.color.white_50));
						((TextView) currentTabView.findViewById(R.id.tab_text))
								.setTextColor(NoticeActivity.this.getResources().getColor(R.color.white));
					}
				});
		myFragmentLayout.setAdapter(fragBaseFragments, R.layout.tablayout_notice,
				Cantent.VIEWPAGER_ID_NOTICE);

		textView_config = (TextView) myFragmentLayout.findViewById(R.id.textView_config);
		textView_config.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NoticeActivity.this, NoticeConfigActivity.class);
				NoticeActivity.this.startActivity(intent);
			}
		});
	}
}
