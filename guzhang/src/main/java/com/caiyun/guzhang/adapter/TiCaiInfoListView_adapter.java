package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.javabean.StockData;
import com.caiyun.guzhang.util.MyAPP;

import java.util.ArrayList;

public class TiCaiInfoListView_adapter extends BaseAdapter {
	private Context context;
	private ArrayList<StockData> mList;
	public TiCaiInfoListView_adapter(Context context, ArrayList<StockData> mList) {
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
			convertView=View.inflate(context, R.layout.item_ticaiinfo_listview, null);
			holder=new HolderView();
			holder.txt_name = (TextView) convertView.findViewById(R.id.textView7);
			holder.txt_code = (TextView) convertView.findViewById(R.id.textView8);
			holder.txt_price = (TextView) convertView.findViewById(R.id.textView9);
			holder.txt_zdf = (TextView) convertView.findViewById(R.id.textView10);
			holder.longtou = (TextView) convertView.findViewById(R.id.text_longtou);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		StockData item = mList.get(position);
		holder.txt_name.setText(item.getName());
		holder.txt_code.setText(item.getCode());
		holder.txt_price.setText(String.valueOf(item.getPrice()));
		holder.txt_zdf.setText(String.valueOf(item.getRise())+"%");
		if (item.getIslongtou() == 1) {
			holder.longtou.setVisibility(View.VISIBLE);
		} else {
			holder.longtou.setVisibility(View.GONE);
		}
		if (item.getRise() >= 0) {
			holder.txt_price.setTextColor(MyAPP.red);
			holder.txt_zdf.setTextColor(MyAPP.red);
		} else {
			holder.txt_price.setTextColor(MyAPP.green);
			holder.txt_zdf.setTextColor(MyAPP.green);
		}
		return convertView;
	}
	
	static class HolderView{
		TextView txt_name,txt_code,txt_price,txt_zdf,txt,longtou;
		
	}
}
