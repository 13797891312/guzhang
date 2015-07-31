package com.caiyun.guzhang.util;


import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response.Listener;
import com.zhaojin.myviews.CustomProgressDialog;
import com.zhaojin.utils.LogUtils;

import org.json.JSONObject;

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

			if (response.getInt("ret") != 200) {
				Toast.makeText(context, ErrorCode.getString(response.getInt("ret")), Toast.LENGTH_SHORT).show();
				onRet_200(response);
				return;
			} else {
					onSucess(response);
			}

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
	public void onSucess(JSONObject response) throws Exception{
		
	}
	/**
	 * 
	 * @param response ret!=200 服务器返回数据错误
	 */
	public void onRet_200(JSONObject response){
		
	}

	/**
	 *
	 * @param response code!=0 服务器成功返回，但数据错误
	 */
	public void onCode_0(JSONObject response){

	}

}
