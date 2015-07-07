package com.caiyun.guzhang.util;

import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.LogUtils;

public class VolleyListerner implements Listener<JSONObject>{
	Context context;
	CustomProgressDialog dialog;
	public VolleyListerner(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public VolleyListerner(Context context,CustomProgressDialog dialog) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.dialog=dialog;
	}
	@Override
	public void onResponse(JSONObject response) {
		// TODO Auto-generated method stub
		LogUtils.e("JSONObject", response.toString());
		CustomProgressDialog.dismissDialog(dialog);
		try {
			if (response.getInt("code")!=0) {
				Toast.makeText(context, ErrorCode.getString(response.getInt("code")), Toast.LENGTH_SHORT).show();
				onRet_0(response);
				return;
			}
			onSucess(response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(context, "请求出错", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * 
	 * @param response ret=0 成功
	 */
	public void onSucess(JSONObject response){
		
	}
	/**
	 * 
	 * @param response ret!=0 服务器返回数据错误
	 */
	public void onRet_0(JSONObject response){
		
	}

}
