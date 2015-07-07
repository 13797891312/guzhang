package com.caiyun.guzhang.fragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.caiyun.app.guzhang.R;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment3 extends BaseFragment {
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context=this.getActivity();
		if (view != null) {
			return view;
		}
		view = inflater.inflate(R.layout.fragment_3, container, false);
		return view;
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
}
