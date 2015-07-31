package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.javabean.NewsData;
import com.zhaojin.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class Fragment_news_adapter extends BaseAdapter {
	private Context context;
	private ArrayList<NewsData> mList;
	public Fragment_news_adapter(Context context, ArrayList<NewsData> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
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
			convertView=View.inflate(context, R.layout.item_news_listview, null);
			holder=new HolderView();
			holder.txt_text = (TextView) convertView.findViewById(R.id.txt_text);
			holder.txt_time = (TextView) convertView.findViewById(R.id.txt_time);
			holder.txt_title = (TextView) convertView.findViewById(R.id.txt_title);
			holder.txt_from = (TextView) convertView.findViewById(R.id.txt_from);
			holder.circle = convertView.findViewById(R.id.circle);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		NewsData item = mList.get(position);
		holder.txt_text.setText(item.getText());
		holder.txt_title.setText(item.getTitle());
		holder.txt_from.setText("[来源: "+item.getClassname()+"]");
		holder.txt_time.setText(item.getTime());
		if (position == 0) {
			holder.circle.setBackgroundResource(R.drawable.shape_red_timeline_circle);
		} else {
			holder.circle.setBackgroundResource(R.drawable.shape_other_timeline_circle);
		}
		return convertView;
	}
	
	static class HolderView{
		TextView txt_title,txt_time,txt_text,txt_from;
		View circle;
	}

}
