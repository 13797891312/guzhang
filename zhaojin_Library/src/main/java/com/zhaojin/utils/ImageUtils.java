package com.zhaojin.utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.mylibrary.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageUtils {
	
	/**
	 * 
	 * @return 需要缓存
	 */
	public static DisplayImageOptions getOption(){
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder() 
        .showImageForEmptyUri(R.drawable.ic_launcher)
        .showImageOnFail(R.drawable.ic_launcher) 
        .showImageOnLoading(R.drawable.ic_launcher)
        .imageScaleType(ImageScaleType.EXACTLY)
        .bitmapConfig(Config.RGB_565)
        .cacheInMemory(true) 
        .cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(300)).build();
		return displayImageOptions;
	}
	/**
	 * 
	 * @param r 圆角半径
	 * @return 需要缓存 带圆角
	 */
	public static DisplayImageOptions getOption(int r){
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder() 
		.showImageForEmptyUri(R.drawable.ic_launcher)//地址为空时加载的图片
		.showImageOnFail(R.drawable.ic_launcher) //加载失败显示的图片
		.showImageOnLoading(R.drawable.ic_launcher)
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Config.RGB_565)//图片显示清晰度
		.displayer(new RoundedBitmapDisplayer(r))//设置圆角
		.cacheInMemory(true) 
		.cacheOnDisk(true)
		.build();
		return displayImageOptions;
	}
	/**
	 * 
	 * @return 不需要任何缓存
	 */
	public static DisplayImageOptions getNoCacheOption(){
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder() 
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher) 
		.showImageOnLoading(R.drawable.ic_launcher)
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Config.RGB_565)
		.cacheInMemory(false) 
		.cacheOnDisk(false)
		.displayer(new FadeInBitmapDisplayer(300))
		.build();
		return displayImageOptions;
	}
	
	/**
	 * 
	 * @return 只用sd卡缓存；
	 */
	public static DisplayImageOptions getOption1(){
		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder() 
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher) 
		.showImageOnLoading(R.drawable.ic_launcher)
		.imageScaleType(ImageScaleType.EXACTLY)
		.bitmapConfig(Config.RGB_565)
		.cacheInMemory(false) 
		.cacheOnDisk(true).displayer(new FadeInBitmapDisplayer(300))
		.imageScaleType(ImageScaleType.EXACTLY).build();
		return displayImageOptions;
	}
//	/**
//	 * 
//	 * @return 只用内存缓存；
//	 */
//	public static DisplayImageOptions getOption2(){
//		DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder() 
//		.showImageForEmptyUri(R.drawable.ic_launcher)
//		.showImageOnFail(R.drawable.ic_launcher) 
//		.imageScaleType(ImageScaleType.EXACTLY)
//		.showImageForEmptyUri(R.drawable.ic_launcher).cacheInMemory(true) 
//		.cacheOnDisk(false).displayer(new FadeInBitmapDisplayer(300))
//		.imageScaleType(ImageScaleType.EXACTLY).build();
//		return displayImageOptions;
//	}
	
	/**
	 * 通过view销毁bitmap
	 */
	public static void rycleBitmap(View v){
		BitmapDrawable drawable;
		Bitmap bmp;
		if (v instanceof ImageView) {
			drawable =(BitmapDrawable)((ImageView) v).getDrawable();
			bmp = drawable.getBitmap();
			if (null != bmp && !bmp.isRecycled()){
				bmp.recycle();
				bmp = null;
			}
			((ImageView) v).setImageBitmap(null);
		}else{
			drawable =(BitmapDrawable)v.getBackground();
			if (drawable instanceof BitmapDrawable) {
				bmp = drawable.getBitmap();
				if (null != bmp && !bmp.isRecycled()){
					bmp.recycle();
					bmp = null;
				}
				v.setBackground(null);
			}
		}
	}
}
