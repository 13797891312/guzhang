package com.caiyun.guzhang.util;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.zhaojin.myviews.CustomProgressDialog;

public class VolleyErrorListoner implements ErrorListener{
	Context context;
	CustomProgressDialog dialog;
	public VolleyErrorListoner(Context context) {
		// TODO Auto-generated constructor stub
		this.context=context;
	}
	public VolleyErrorListoner(Context context,CustomProgressDialog dialog) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.dialog=dialog;
	}
	@Override
	public void onErrorResponse(VolleyError error) {
		// TODO Auto-generated method stub
		CustomProgressDialog.dismissDialog(dialog);
		if (context!=null) {
			Toast.makeText(context, VolleyErrorHelper.getMessage(error, context), Toast.LENGTH_SHORT).show();
		}
		onError(error);
	}
	
	public void onError(VolleyError error){
		
	}
}
