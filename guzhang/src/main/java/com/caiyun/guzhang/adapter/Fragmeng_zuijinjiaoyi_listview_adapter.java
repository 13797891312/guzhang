package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.view.CircleImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

public class Fragmeng_zuijinjiaoyi_listview_adapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> mList;
	private DisplayImageOptions option;
	public Fragmeng_zuijinjiaoyi_listview_adapter(Context context, ArrayList<String> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
		this.option = getOption();
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
			convertView=View.inflate(context, R.layout.item_zuijinjiaoyi_listview, null);
			holder=new HolderView();
			holder.iv = (CircleImageView) convertView.findViewById(R.id.imageView);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/F4F9A9E2F51379AAEBB984E0E20E551F/100",holder.iv,option);
		return convertView;
	}
	
	static class HolderView{
		CircleImageView iv;
		
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
