package com.caiyun.guzhang.adapter;

import java.util.ArrayList;

import com.caiyun.app.guzhang.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> mList; 
	public MyAdapter(Context context,ArrayList<String> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
//		return mList.size();
		return 10;
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
			convertView=View.inflate(context, R.layout.fragment_1, null);
			holder=new HolderView();
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		return convertView;
	}
	
	static class HolderView{
		
	}

	public DisplayImageOptions getOption() {
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.defalut_user_icon)
				// 地址为空时加载的图片
//				.showImageOnFail(R.drawable.defalut_user_icon)
				// 加载失败显示的图片
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)// 图片显示清晰度
				.cacheInMemory(true).cacheOnDisk(true).build();
		return displayImageOptions;
	}

}
