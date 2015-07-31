package com.caiyun.guzhang.util;


import com.zhaojin.utils.StringUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SaveDate {
	//###########################参数区 开始##############################################
	private static SaveDate SAVEDATE;
	
	//上下文
	private Context con;
	
	 
	SharedPreferences sharedPreferences;
		
	//是否是第一次登录标识
	private boolean isOnce = true;
	
	
	private String version;
	//登陆账号
	private String uid;
	
	//登陆密码
	private String pwd;

	//是否有模拟插入股票数据
	private boolean isInsertData;
	
	//############################参数区 结束#############################################
	
	
	
	//#####################################################################
	private SaveDate(Context con) {
		this.con = con;
		if (sharedPreferences == null) {
			sharedPreferences = con.getSharedPreferences("saveDate",Context.MODE_WORLD_WRITEABLE);
		}
	}
	/***得到一个单例对象***/
	public static SaveDate getInstence(Context con) {
		if (SAVEDATE == null) {
			SAVEDATE = new SaveDate(con);
			SAVEDATE.readDate();
		}
		return SAVEDATE;
	}
	//#########################################################################
	
	
	
	//#####################读数据/写数据 开始#################################################
	public void saveDate() {
		Editor ed = sharedPreferences.edit();
		ed.putBoolean("isOnce", isOnce);
		ed.putBoolean("isInsertData", isInsertData);
		ed.putString("version", version);
		ed.putString("uid", uid);
		ed.putString("pwd", pwd);
		ed.commit();
	}
	public void readDate() {
		isOnce = sharedPreferences.getBoolean("isOnce", true);
		version = sharedPreferences.getString("version", "");
		uid = sharedPreferences.getString("uid", "");
		pwd = sharedPreferences.getString("pwd", "");
		isInsertData=sharedPreferences.getBoolean("isInsertData", false);
	}
	//######################读数据/写数据 结束################################################
	
	
	
	//#########################get/set方法区 开始################################################
	/**
	 * 
	 * @return isOnce  
	 */
	public boolean isInsertData() {
		readDate();
		return isInsertData;
	}

	public void setIsInsertData(boolean isInsertData) {
		this.isInsertData = isInsertData;
		saveDate();
	}

	/**
	 *
	 * @return isOnce
	 */
	public boolean isOnce() {
		readDate();
		return isOnce;
	}

	public void setIsonce(boolean isOnce) {
		this.isOnce = isOnce;
		saveDate();
	}
	
	
	
	public String getVersion() {
		readDate();
		if (StringUtils.isNullOrBlanK(version)) {
			version="";
		}
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
		saveDate();
	}
	
	public String getUid() {
		readDate();
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
		saveDate();
	}
	public String getPwd() {
		readDate();
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
		saveDate();
	}

	//##########################get/set方法区 结束###############################################
}
