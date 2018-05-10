package com.ganger.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeUtil {
	
	public static String showTime(Timestamp ctime) {
		String r = "";
		if(ctime==null)return r;
//		String format="yyyy-MM-dd HH:mm";
		String format="yyyy-MM-dd";
		long nowtimelong = System.currentTimeMillis();
		long ctimelong = ctime.getTime();
		long result = Math.abs(nowtimelong - ctimelong);
		if (result < 60000)// 一分钟内
		{
			long seconds = result / 1000;
		r = seconds + "秒钟前";
		} 
		else if (result >= 60000 && result < 3600000) {
			// 一小时内
			long seconds = result / 60000;
		r = seconds + "分钟前";
		} 
		else if (result >= 3600000 && result < 86400000)// 一天内
		{
			long seconds = result / 3600000;
		r = seconds + "小时前";
		} 
		else// 日期格式
		{
			r = new SimpleDateFormat(format).format(ctime);
		}
		return r;
		}
}
