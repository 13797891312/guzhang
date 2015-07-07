package com.zhaojin.activity;

import java.util.ArrayList;
import java.util.List;

import com.zhaojin.utils.ImageUtils;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity{
	protected List<Integer> imageIdList=new ArrayList<Integer>();
	
	public void backClick(View v){
		this.finish();
	}
	
	public void setTitle(int id,String title){
		((TextView)findViewById(id)).setText(title);
	}
	
	/**
	 * 添加需要销毁bitmap的view的ID(包括imageview和其他有设置过图片做背景的view)
	 * @param res ID
	 */
	protected void addImage(int id){
		imageIdList.add(id);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
	}
}
