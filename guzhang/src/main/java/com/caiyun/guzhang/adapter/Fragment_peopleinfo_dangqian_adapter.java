package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.javabean.NewsData;

import java.util.ArrayList;

public class Fragment_peopleinfo_dangqian_adapter extends BaseAdapter {
	private Context context;
	private ArrayList<NewsData> mList;
	public Fragment_peopleinfo_dangqian_adapter(Context context, ArrayList<NewsData> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
//		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		HolderView holder;
		if (convertView==null) {
			convertView=View.inflate(context, R.layout.item_peopleinfo_dangqian_listview, null);
			holder=new HolderView();
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		return convertView;
	}
	
	static class HolderView{
	}
}
