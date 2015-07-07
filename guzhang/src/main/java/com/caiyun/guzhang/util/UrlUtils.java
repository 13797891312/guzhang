package com.caiyun.guzhang.util;

import com.zhaojin.utils.LogUtils;

import java.text.DecimalFormat;

public class UrlUtils {
	public static String httpsAdress="https://api.68baobao.cn:7443/openapi/v2";
	public static String httpAdress="http://api.68baobao.cn:8088/openapi/v2";
	public static  DecimalFormat dFormat = new DecimalFormat("#.##");
	
//	public static String httpsAdress="https://59.175.153.70/openapi/v2";
//	public static String httpAdress="http://59.175.153.70:8088/openapi/v2";//15107102907 102907
	
	/**
	 * 通知图片上传
	 */
//	public static final String NotifyUploadAdress="http://sdboxupload.68baobao.cn/Upload/schoolNoticeImgUpload";//(测试）通知图片
	public static final String NotifyUploadAdress="http://upload.68baobao.cn/Upload/schoolNoticeImgUpload";//正式通知图片
	
//	public static final String HeaderUploadAdress="http://sdboxupload.68baobao.cn/Upload/headImageUpload";//(测试）//头像上传
	public static final String HeaderUploadAdress="http://upload.68baobao.cn/Upload/headImageUpload";//正式  头像上传
	
//	public static final String ClassGroupUploadAdress="http://sdboxupload.68baobao.cn/Upload/socialUpload";//测试  班级圈上传
	public static final String ClassGroupUploadAdress="http://upload.68baobao.cn/Upload/socialUpload";//正式     班级圈上传
	
//	public static final String ClassGroupVideoAdress="http://sdboxupload.68baobao.cn/Upload/videoUpload";//测试  视频
	public static final String ClassGroupVideoAdress="http://upload.68baobao.cn/Upload/videoUpload";//正式     视频
	

	/*** 获取https URL ***/
	public static  String getHttpsUrl(String function,String key[], String value[]) {
		String url = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length; i++) {
			sb.append(key[i] + "=");
			LogUtils.e("参数名", key[i] + "=" + value[i]);
			try {
				sb.append(value[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sb.append("&");
		}
		sb.delete(sb.length() - 1, sb.length());
			url =httpsAdress + function+"?" +sb.toString();
			LogUtils.e("url", url);
		return url;
	}
	
	/*** 获取http URL ***/
	public static  String getHttpUrl(String function,String key[], String value[]) {
		String url = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < value.length; i++) {
			sb.append(key[i] + "=");
			LogUtils.e("参数名", key[i] + "=" + value[i]);
			try {
				sb.append(value[i]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sb.append("&");
		}
		sb.delete(sb.length() - 1, sb.length());
		url = httpAdress + function+"?" +sb.toString();
		LogUtils.e("url", url);
		return url;
	}
}
