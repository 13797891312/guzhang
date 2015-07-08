package com.caiyun.guzhang.util;

import com.zhaojin.utils.LogUtils;

import java.text.DecimalFormat;

public class UrlUtils {
	public static String httpAdress="http://api.guzhang.com/v1?service=";
	public static  DecimalFormat dFormat = new DecimalFormat("#.##");

	/*** 获取http URL ***/
	public static  String getHttpUrl(String function,String key[], String value[]) {
		String url = "";
		StringBuffer sb = new StringBuffer();
		sb.append(httpAdress).append(function);
		for (int i = 0; i < value.length; i++) {
			sb.append("&");
			sb.append(key[i] + "=");
			LogUtils.e("参数名", key[i] + "=" + value[i]);
			try {
				sb.append(value[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		url =sb.toString();
		LogUtils.e("url", url);
		return url;
	}
}
