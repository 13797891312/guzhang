package com.caiyun.guzhang.view;
import java.lang.reflect.Field;

import com.zhaojin.utils.LogUtils;

import android.content.Context;  
import android.media.MediaPlayer;
import android.util.AttributeSet;  
import android.widget.VideoView;  
  
public class MyVideoView extends VideoView {  
  
    public MyVideoView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    public MyVideoView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public MyVideoView(Context context) {  
        super(context);  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        // TODO Auto-generated method stub  
        int width = getDefaultSize(0, widthMeasureSpec);  
        int height = getDefaultSize(0, heightMeasureSpec);  
        setMeasuredDimension(width, height);  
    }  
    
    /**
     * 
     * @return 通过反射得到私有对象MediaPlayer
     */
    public MediaPlayer getMediaPlayer(){
    	 Field field;
		try {
			field = VideoView.class.getDeclaredField("mMediaPlayer");
			// 设置访问权限（这点对于有过android开发经验的可以说很熟悉）
			field.setAccessible(true);
			// 得到私有的变量值
			MediaPlayer mediaPlayer  = (MediaPlayer) field.get(this);
			// 输出私有变量的值
			return mediaPlayer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
