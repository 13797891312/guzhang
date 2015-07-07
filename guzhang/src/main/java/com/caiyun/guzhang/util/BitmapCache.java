package com.caiyun.guzhang.util;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import android.graphics.Bitmap;

public class BitmapCache implements ImageCache {  
	  
    private com.caiyun.guzhang.util.ImageCache mCache;  
  
    public BitmapCache() {  
        int maxSize = 10 * 1024 * 1024;  
        mCache = new com.caiyun.guzhang.util.ImageCache(maxSize) ;
    }  
  
    @Override  
    public Bitmap getBitmap(String url) {  
        return mCache.get(url);  
    }  
  
    @Override  
    public void putBitmap(String url, Bitmap bitmap) {  
        mCache.put(url, bitmap);  
    }  
  
}  
