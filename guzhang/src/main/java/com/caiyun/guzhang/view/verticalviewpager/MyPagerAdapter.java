package com.caiyun.guzhang.view.verticalviewpager;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.NewsInfoActivity;
import com.caiyun.guzhang.javabean.NewsData;

public class MyPagerAdapter extends PagerAdapter {
	private Context context;
	private ArrayList<NewsData> mList;
	private static final String TAG = "PagerAdapter";

	public MyPagerAdapter(Context context, ArrayList<NewsData> mList) {
		this.context = context;
		this.mList = mList;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		TextView tv = (TextView) View.inflate(context, R.layout.textview_viewpager,null);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, NewsInfoActivity.class);
				intent.putExtra("id", mList.get(position).getId());
				context.startActivity(intent);
			}
		});
		tv.setText(mList.get(position).getTitle());
		container.addView(tv);
		return tv;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
