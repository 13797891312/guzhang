package com.caiyun.guzhang.util;

import com.zhaojin.utils.StringUtils;

//0	成功	-100	【？】参数为空或格式错误
//1	用户不存在	-101	时间窗口开始时间大于结束时间
//2	账号或密码错误	-102	翻页数据错误
//3	账号无法使用		
//4	Token不存在		
//5	Token错误(
//提示账号已在其他设备登陆，退出app)		
//101	系统错误		
//200	资源错误（所提交的参数格式完整，但资源不对应或不存在）		

public class ErrorCode {
	public static String getString(int errorCode) {
		String result = null;
		switch (errorCode) {
		case -100:
			result = "请求出错";
			break;
		case -102:
			result = "请求出错";
			break;
		case 1:
			result = "用户不存在";
			break;
		case 2:
			result = "账号或密码错误";
			break;
		case 3:
			result = "该账号无法使用";
			break;
		case 4:
			result = "Token不存在";
			break;
		case 5:
			result = "登陆已失效，请重新登陆";
			break;
		case 6:
			result = "短信验证码输入错误";
			break;
		case 101:
			result = "系统错误";
			break;
		case 200:
			result = "资源错误";
			break;
		default:
			break;
		}
		return StringUtils.isNullOrBlanK(result) ? "" + errorCode : result;
	}
}
