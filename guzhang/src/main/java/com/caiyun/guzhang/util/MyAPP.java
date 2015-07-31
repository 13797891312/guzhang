package com.caiyun.guzhang.util;


import android.content.Context;
import android.graphics.Typeface;

import java.io.File;

import org.litepal.LitePalApplication;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import cn.jpush.android.api.JPushInterface;

public class MyAPP extends LitePalApplication  {
	public static String token;
	public static int green=0xff51bf00;
	public static int red = 0xffF12A34;
	private static Typeface typeFace;// 应用字体  textView.setTypeface(typeFace);
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		initImageLoader();
	}
	// 初始化图片加载框架
			private void initImageLoader() {
				// TODO Auto-generated method stub
				ImageLoaderConfiguration conf = new ImageLoaderConfiguration.Builder(
						this)
						.diskCache(new UnlimitedDiskCache(new File(Cantent.CACHEPATH)))
						.diskCacheSize(50 * 1024 * 1024)
						.memoryCache(new WeakMemoryCache())
						.memoryCacheSize(10 * 1024 * 1024).threadPoolSize(5).build();
				ImageLoader.getInstance().init(conf);
			}
	public static Typeface getTypeFace(Context context) {
		if (typeFace == null) {
			typeFace = Typeface.createFromAsset(context.getAssets(), "fonts/typefount.ttc");
		}
		return  typeFace;
	}
}
