package com.caiyun.guzhang.fragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.caiyun.app.guzhang.R;
import com.caiyun.guzhang.MainActivity;
import com.caiyun.guzhang.TransactionActivity;
import com.caiyun.guzhang.util.ImageUtils;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

public class Fragment2 extends BaseFragment implements OnClickListener{
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_2, container, false);
		findView();
		return view;
	}
	
	private void findView() {
//		iv_bg.setImageBitmap(ImageUtils.blurBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher), context));
		LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT, (int) ((MainActivity.screenWidth-16*MainActivity.screenScale)/4));
		view.findViewById(R.id.line1).setLayoutParams(params);
		view.findViewById(R.id.line2).setLayoutParams(params);
		view.findViewById(R.id.transaction).setOnClickListener(this);
	}

	public DisplayImageOptions getOption() {
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
//				.showImageForEmptyUri(R.drawable.defalut_user_icon)
				// 地址为空时加载的图片
//				.showImageOnFail(R.drawable.defalut_user_icon)
				// 加载失败显示的图片
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Config.RGB_565)// 图片显示清晰度
				.cacheInMemory(true).cacheOnDisk(true).build();
		return displayImageOptions;
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.transaction:
			intent =new Intent(context, TransactionActivity.class);
			this.startActivity(intent);
			break;
		}
	}
}
