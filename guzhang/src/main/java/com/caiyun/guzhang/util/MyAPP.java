package com.caiyun.guzhang.util;


import java.io.File;

import org.litepal.LitePalApplication;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyAPP extends LitePalApplication  {
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
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
}
