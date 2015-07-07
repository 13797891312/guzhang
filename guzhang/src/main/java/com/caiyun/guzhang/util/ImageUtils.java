package com.caiyun.guzhang.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class ImageUtils {

	public static void desplayImage(RequestQueue mQueue, String url,
			final ImageView imageView, final int fieldId) {
		ImageRequest imageRequest = new ImageRequest(url,
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap response) {
						imageView.setImageBitmap(response);
					}
				}, 0, 0, Config.RGB_565, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						imageView.setImageResource(fieldId);
					}
				});
		mQueue.add(imageRequest);
	}
	
	/**
	 * 
	 * @param bitmap
	 * @param context
	 * @return  模糊图片
	 */
	public static Bitmap blurBitmap(Bitmap bitmap,Context context){  
        //Let's create an empty bitmap with the same size of the bitmap we want to blur  
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);  
        //Instantiate a new Renderscript  
        android.support.v8.renderscript.RenderScript rs = RenderScript.create(context);  
          
        //Create an Intrinsic Blur Script using the Renderscript  
        android.support.v8.renderscript.ScriptIntrinsicBlur blurScript = android.support.v8.renderscript.ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));  
          
        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps  
        android.support.v8.renderscript.Allocation allIn = android.support.v8.renderscript.Allocation.createFromBitmap(rs, bitmap);  
        android.support.v8.renderscript. Allocation allOut = android.support.v8.renderscript.Allocation.createFromBitmap(rs, outBitmap);  
          
        //Set the radius of the blur  
        blurScript.setRadius(20.f);  
        //Perform the Renderscript  
        blurScript.setInput(allIn);  
        blurScript.forEach(allOut);  
          
        //Copy the final bitmap created by the out Allocation to the outBitmap  
        allOut.copyTo(outBitmap);  
          
        //recycle the original bitmap  
        bitmap.recycle();  
          
        //After finishing everything, we destroy the Renderscript.  
        rs.destroy();  
        return outBitmap;  
    } 
}
