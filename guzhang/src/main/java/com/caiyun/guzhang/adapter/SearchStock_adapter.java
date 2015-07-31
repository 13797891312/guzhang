package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.javabean.ModelStockData;

import java.util.ArrayList;
import java.util.List;

public class SearchStock_adapter extends BaseAdapter {
	private Context context;
	private List<ModelStockData> mList;
	public SearchStock_adapter(Context context, List<ModelStockData> mList) {
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
			convertView=View.inflate(context, R.layout.item_searchstock_listview, null);
			holder=new HolderView();
			holder.tv = (TextView) convertView.findViewById(R.id.textView1);
			holder.btn = (Button) convertView.findViewById(R.id.button);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		final ModelStockData item = mList.get(position);
		holder.tv.setText(item.getName()+"  （"+item.getCode()+"）");
		holder.btn.setBackgroundResource(item.getIsZixuan() == 1 ? R.drawable.select_zixuan_jian_btn : R.drawable.select_zixuan_btn);
		holder.btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (item.getIsZixuan() == 0) {
					item.setIsZixuan(1);
					item.update(item.getId());
				} else {
					item.setIsZixuan(0);
					item.setToDefault("isZixuan");
				}
				item.update(item.getId());
				SearchStock_adapter.this.notifyDataSetChanged();
			}
		});
		return convertView;
	}
	
	static class HolderView{
		TextView tv;
		Button btn;
	}

}
