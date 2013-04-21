package com.lyw.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestDate {

	public static void main(String[] args) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
//		String str = sdf.format(Calendar.getInstance().getTime());
//		String str1 = "2013-12-01";
//		try {
//			Date d = sdf.parse(str1);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		System.out.println(new TestDate().getAge("1991-02-27"));
		System.out.println(getLoginTime("001359946502639"));
	}

	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  hh:mm");
	private static SimpleDateFormat forDB=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
	public static String  format(Date d){
		return sdf.format(d);
	}
	public static Date parse(String str){
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Date parses(String str){
		try {
			return forDB.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getNowDate() {
		String str = forDB.format(Calendar.getInstance().getTime());
		return str;
	}
	
	public static String getAge(String birthday) {
		
		Date birth = parses(birthday);
		long lon = (System.currentTimeMillis() - birth.getTime()) / (24 * 60 * 60 * 1000);
		lon = lon / 365;
		return lon+"";
	}
	
	/**
	 * @param birthday
	 * @return
	 */
	public static String getLoginTime(String modificatianDate) {
		Date temp = new Date(Long
					.parseLong("001360050564614"));
		System.out.println(format(temp));
		long time = 0;
		if (modificatianDate != null && !"".equals(modificatianDate)) {
			time = (System.currentTimeMillis() - Long
					.parseLong(modificatianDate)) / (60 * 1000);
		}
		return time+"";
	}

}
