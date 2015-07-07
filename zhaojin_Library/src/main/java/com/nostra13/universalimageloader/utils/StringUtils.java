package com.nostra13.universalimageloader.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 

public class StringUtils {
	
	private static SimpleDateFormat dateFormat = null;
	static {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
	}

	
	  /**
     * 
     * @创建时间：Feb 17, 2013 9:23:14 PM
     * @方法描述：（对象为空字符或null或集合长度为0）
     * @返回值：true/false
     * @version
     */
    public static boolean isNullOrBlanK(Object param) {
    	 if(param==null){
         	return true;
         }else{
         	if(java.lang.String.class.isInstance(param)){
         		if(!"".equals(((String) param).trim())){
         			return false;
         		}
         	}else if(java.util.List.class.isInstance(param)){
         		if(((ArrayList) param).size()!=0){
         			return false;
         		}
         	}else if(java.util.Map.class.isInstance(param)){
         		if(((HashMap) param).size()!=0){
         			return false;
         		}
         	}else if(java.lang.String[].class.isInstance(param)){
         		if(((Object[]) param).length!=0){
         			return false;
         		}
         	}else{
         		return false;
         	}
         	return true;
         }
    }
    
    /**** 是否是有效电话 ****/
	public static boolean isPhone(String name) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(17[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(name);
		return m.matches();
	}
	
	/*** 是否是有效Email ***/
	public static boolean isEmail(String email) {
		boolean flag = true;
		if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {

			flag = false;
		}
		if (flag) {
			if (email.indexOf("@") > email.indexOf("."))
				flag = false;
		}
		return flag;
	}
	
	/** 是否是有效密码 ***/
	public static boolean isGoodPWD(String pwd) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9_]{6,16}$");
		Matcher m = p.matcher(pwd);
		return m.matches();
	}
	
	/** 是否是有效日期 ***/
	public static boolean isGoodDate(String s) {
		try {
			dateFormat.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
}
