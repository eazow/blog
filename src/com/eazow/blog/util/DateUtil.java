package com.eazow.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	@SuppressWarnings("deprecation")
	public static String getTodayMinTime(Date today)
	{
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		return today.toLocaleString();
	}
	
	@SuppressWarnings("deprecation")
	public static String getTodayMaxTime(Date today)
	{
		today.setHours(23);
		today.setMinutes(59);
		today.setSeconds(59);
		return today.toLocaleString();
	}
	
	//获取这个月最早一天
	public static String getFirstDayOfMonth(int year, int month)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		Date firstDay = calendar.getTime();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		return simpleDateFormat.format(firstDay);
//		calendar.set(year, month, 1);
//		Date afterDate = calendar.getTime();
//		String afterDateStr = simpleDateFormat.format(afterDate);
//		int realArticlesNumByArticleArchive = this.articleDAO.getArticlesNumByArticleArchive(beforeDateStr, afterDateStr);
//		articleArchive.setRealArticlesNum(realArticlesNumByArticleArchive);
	}
	
	public static int getYear(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static int getMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	
	public static Date parseStringToDate(String dateStr)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try
		{
			date = simpleDateFormat.parse(dateStr);
		} 
		catch (ParseException e)
		{
			System.out.println("Parse Exception");
			return null;
		}
		return date;
	}
	
	public static String parseDateToString(Date date)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = simpleDateFormat.format(date);
		return dateStr;
	}
	
}
