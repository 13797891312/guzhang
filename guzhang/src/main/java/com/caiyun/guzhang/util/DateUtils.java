package com.caiyun.guzhang.util;

/* 
 *  Copyright  sunflower 
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0 
 * 
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 *  See the License for the specific language governing permissions and 
 *  limitations under the License. 
 * 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.zip.DataFormatException;

/**
 * 日期时间工具类
 * 
 * @author sunflower
 * 
 */
public class DateUtils {
	/**"yyyy-MM-dd HH:mm:ss"***/
	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/*****"yyyy-MM-dd"******/
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");
	/*****"HH:mm:ss"*******/
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat(
			"HH:mm:ss");
	/***"HH:mm"***/
	public static final SimpleDateFormat timeFormat1 = new SimpleDateFormat(
			"HH:mm");

	/**
	 * 获得当前日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentDatetime() {
		return datetimeFormat.format(now());
	}

	/**
	 * 格式化日期时间
	 * <p>
	 * 日期时间格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatDatetime(Date date) {
		return datetimeFormat.format(date);
	}

	/**
	 * 格式化日期时间
	 * 
	 * @param date
	 * @param pattern
	 *            格式化模式，详见{@link SimpleDateFormat}构造器
	 *            <code>SimpleDateFormat(String pattern)</code>
	 * @return
	 */
	public static String formatDatetime(Date date, String pattern) {
		SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat
				.clone();
		customFormat.applyPattern(pattern);
		return customFormat.format(date);
	}

	/**
	 * 获得当前日期
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		return dateFormat.format(now());
	}

	/**
	 * 格式化日期
	 * <p>
	 * 日期格式yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 获得当前时间
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime() {
		return timeFormat.format(now());
	}

	/**
	 * 格式化时间
	 * <p>
	 * 时间格式HH:mm:ss
	 * 
	 * @return
	 */
	public static String formatTime(Date date) {
		return timeFormat.format(date);
	}

	/**
	 * 获得当前时间的<code>java.util.Date</code>对象
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date();
	}

	public static Calendar calendar() {
		Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		return cal;
	}

	/**
	 * 获得当前时间的毫秒数
	 * <p>
	 * 详见{@link System#currentTimeMillis()}
	 * 
	 * @return
	 */
	public static long millis() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * 获得当前Chinese月份
	 * 
	 * @return
	 */
	public static int month() {
		return calendar().get(Calendar.MONTH) + 1;
	}

	/**
	 * 获得月份中的第几天
	 * 
	 * @return
	 */
	public static int dayOfMonth() {
		return calendar().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 今天是星期的第几天
	 * 
	 * @return
	 */
	public static int dayOfWeek() {
		return calendar().get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 今天是年中的第几天
	 * 
	 * @return
	 */
	public static int dayOfYear() {
		return calendar().get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 判断原日期是否在目标日期之前
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isBefore(Date src, Date dst) {
		return src.before(dst);
	}

	/**
	 * 判断原日期是否在目标日期之后
	 * 
	 * @param src
	 * @param dst
	 * @return
	 */
	public static boolean isAfter(Date src, Date dst) {
		return src.after(dst);
	}

	/**
	 * 判断两日期是否相同
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isEqual(Date date1, Date date2) {
		return date1.compareTo(date2) == 0;
	}

	/**
	 * 判断某个日期是否在某个日期范围
	 * 
	 * @param beginDate
	 *            日期范围开始
	 * @param endDate
	 *            日期范围结束
	 * @param src
	 *            需要判断的日期
	 * @return
	 */
	public static boolean between(Date beginDate, Date endDate, Date src) {
		return beginDate.before(src) && endDate.after(src);
	}

	/**
	 * 获得当前月的最后一天
	 * <p>
	 * HH:mm:ss为0，毫秒为999
	 * 
	 * @return
	 */
	public static Date lastDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
		cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
		return cal.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * <p>
	 * HH:mm:ss SS为零
	 * 
	 * @return
	 */
	public static Date firstDayOfMonth() {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
		cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
		cal.set(Calendar.MINUTE, 0);// m置零
		cal.set(Calendar.SECOND, 0);// s置零
		cal.set(Calendar.MILLISECOND, 0);// S置零
		return cal.getTime();
	}

	private static Date weekDay(int week) {
		Calendar cal = calendar();
		cal.set(Calendar.DAY_OF_WEEK, week);
		return cal.getTime();
	}

	/**
	 * 获取上周的日期
	 * 
	 * @param day
	 *            参数为Calendar.MONDAY为获取星期一的日期，以此类推
	 * @return 2145-03-02格式字符串
	 */
	public static String week(int day) {

		return dateFormat.format(weekDay(day + 1));
	}

	public static Date parseDatetime(String datetime) throws ParseException {
		return datetimeFormat.parse(datetime);
	}

	/**
	 * 根据日期获得所在周的日期 星期一到星期5
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static void dateToWeek(Date mdate, ArrayList<Date> mlist) {
		mlist.clear();
		int b = mdate.getDay();
		Date fdate;
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		for (int a = 1; a <= 5; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			mlist.add(a - 1, fdate);
		}
	}
	
	/**
	 * 根据日期获得所在周的日期，星期1到星期日
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static void dateToWeek7(Date mdate, ArrayList<Date> mlist) {
		mlist.clear();
		int b = mdate.getDay();
		Date fdate;
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		for (int a = 1; a <= 7; a++) {
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			mlist.add(a - 1, fdate);
		}
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 日期时间格式yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * 将字符串日期转换成java.util.Date类型
	 * <p>
	 * 时间格式 HH:mm:ss
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTime(String time) throws ParseException {
		return timeFormat.parse(time);
	}

	/**
	 * 根据自定义pattern将字符串日期转换成java.util.Date类型
	 * 
	 * @param datetime
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDatetime(String datetime, String pattern)
			throws ParseException {
		SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
		format.applyPattern(pattern);
		return format.parse(datetime);
	}

	/**
	 * 毫秒转换成时分秒
	 * @param ms
	 * @return
	 */
	public static String getString(long ms) {
		long hour = ms / (60 * 60 * 1000);
		long minute = (ms - hour * 60 * 60 * 1000) / (60 * 1000);
		long second = (ms - hour * 60 * 60 * 1000 - minute * 60 * 1000) / 1000;
		if (second >= 60) {
			second = second % 60;
			minute += second / 60;
		}
		if (minute >= 60) {
			minute = minute % 60;
			hour += minute / 60;
		}
		String sh = "";
		String sm = "";
		String ss = "";
		if (hour < 10) {
			sh = "0" + String.valueOf(hour);
		} else {
			sh = String.valueOf(hour);
		}
		if (minute < 10) {
			sm = "0" + String.valueOf(minute);
		} else {
			sm = String.valueOf(minute);
		}
		if (second < 10) {
			ss = "0" + String.valueOf(second);
		} else {
			ss = String.valueOf(second);
		}
		return sh + ":" + sm + ":" + ss;
	}
	
	/**
	 * 
	 * @param format1 输入的格式
	 * @param format2输出的格式化
	 * @param string 输入的字符串
	 * @return 返回带今天昨天的日期
	 */
	public static String getDate(SimpleDateFormat format1,SimpleDateFormat format2,String string){
		try {
			Date date=format1.parse(string);
			Date currentDate=new Date();
			Date lastDate=new Date(currentDate.getTime()-24*60*60*1000);
			Date lastLastDate=new Date(currentDate.getTime()-2*24*60*60*1000);
			if (dateFormat.format(date).equals(dateFormat.format(currentDate))) {
				return "今天  "+timeFormat1.format(date);
			}else if (dateFormat.format(date).equals(dateFormat.format(lastDate))) {
				return "昨天  "+timeFormat1.format(date);
			}else if (dateFormat.format(date).equals(dateFormat.format(lastLastDate))) {
				return "前天  "+timeFormat1.format(date);
			}
			return format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 
	 * @param format1 输入的格式
	 * @param format2输出的格式化
	 * @param string 输入的字符串
	 * @return 返回带今天昨天的日期
	 */
	public static String getDate2(SimpleDateFormat format1,SimpleDateFormat format2,String string){
		try {
			Date date=format1.parse(string);
			Date currentDate=new Date();
			Date lastDate=new Date(currentDate.getTime()-24*60*60*1000);
			Date lastLastDate=new Date(currentDate.getTime()-2*24*60*60*1000);
			if (dateFormat.format(date).equals(dateFormat.format(currentDate))) {
				return "今天  ";
			}else if (dateFormat.format(date).equals(dateFormat.format(lastDate))) {
				return "昨天  ";
			}else if (dateFormat.format(date).equals(dateFormat.format(lastLastDate))) {
				return "前天  ";
			}
			return format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 
	 * @param format1 输入的格式
	 * @param format2输出的格式化
	 * @param string 输入的字符串
	 * @return 
	 */
	public static String getDate1(SimpleDateFormat format1,SimpleDateFormat format2,String string){
		try {
			Date date=format1.parse(string);
			return format2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
}
