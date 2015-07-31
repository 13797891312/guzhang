package com.caiyun.guzhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.TransactionActivity;
import com.caiyun.guzhang.view.CircleImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

/****私信适配器****/
public class QuZhuanQianActivity_adapter extends BaseAdapter {
	private Context context;
	private ArrayList<String> mList;
	private DisplayImageOptions options;
	public QuZhuanQianActivity_adapter(Context context, ArrayList<String> mList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.mList=mList;
		options=getOption();
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
//		return mList.get(position);
		return 0;
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
			convertView=View.inflate(context, R.layout.item_notice_guanzhu, null);
			holder=new HolderView();
			holder.iv = (CircleImageView) convertView.findViewById(R.id.imageView);
			holder.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
			holder.txt_zsy = (TextView) convertView.findViewById(R.id.txt_zsy);
			holder.txt_zsyl = (TextView) convertView.findViewById(R.id.txt_zsyl);
			holder.txt_time = (TextView) convertView.findViewById(R.id.textView_time);
			holder.txt_stockName = (TextView) convertView.findViewById(R.id.textView_name);
			holder.txt_count = (TextView) convertView.findViewById(R.id.textView_count);
			holder.txt_price = (TextView) convertView.findViewById(R.id.textView_price);
			holder.txt_zcb = (TextView) convertView.findViewById(R.id.textView_persont);
			holder.btn_buy = (Button) convertView.findViewById(R.id.btn_buy);
			convertView.setTag(holder);
		}else {
			holder=(HolderView) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage("http://q.qlogo.cn/qqapp/1104707878/F4F9A9E2F51379AAEBB984E0E20E551F/100",holder.iv,options);
		holder.btn_buy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context, TransactionActivity.class);
				context.startActivity(intent);
			}
		});
		return convertView;
	}
	
	static class HolderView{
		CircleImageView iv;
		TextView txt_name,txt_zsy,txt_zsyl,txt_time,txt_stockName,txt_count,txt_price,txt_zcb;
		Button btn_buy;
		
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
