package com.lyw.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestDate {

	public static void main(String[] args) {
//		System.out.println("TestDate");
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd",Locale.CHINESE);
		// String str = sdf.format(Calendar.getInstance().getTime());
		// String str1 = "2013-12-01";
		// try {
		// Date d = sdf.parse(str1);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.println(new TestDate().getAge("1991-02-27"));

		// TestDate td = new TestDate();
		// System.out.println(td.parseforDB(getNowDate()));

		// System.out.println(TestDate.getAstro("2013-02-27"));

//		new TestDate().testCalender();

		// System.out.println(td.getAstro(2, 29));

//		System.out.println(getLoginTime("001359946502639"));
//		System.out.println(TestDate.format(yyyyMMddhhMMss, "20130705150124").getHours());
//		System.out.println(TestDate.format(yyyyMMddhhMMss, "20130705150124").compareTo(Calendar.getInstance().getTime()));
		System.out.println(new TestDate().getMillisec(null));
	}

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy年MM月dd日  hh:mm");
	private static SimpleDateFormat yyyyMMddhhMMss = new SimpleDateFormat(
			"yyyyMMddhhMMss");
	private static SimpleDateFormat forDB = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINESE);

	/**
	 * 按指定格式匹配字符串,返回Date
	 * @param sdf
	 * @param str
	 * @return
	 */
	public static Date format(SimpleDateFormat sdf, String str) {
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static String format(Date d) {
		return sdf.format(d);
	}

	public static Date parse(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static Date parses(String str) {
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
		long lon = (System.currentTimeMillis() - birth.getTime())
				/ (24 * 60 * 60 * 1000);
		lon = lon / 365;
		return lon + "";
	}

	/**
	 * @param birthday
	 * @return
	 */
	public static String getLoginTime(String modificatianDate) {
		Date temp = new Date(Long.parseLong("001360050564614"));
		System.out.println(format(temp));
		long time = 0;
		if (modificatianDate != null && !"".equals(modificatianDate)) {
			time = (System.currentTimeMillis() - Long
					.parseLong(modificatianDate)) / (60 * 1000);
		}
		return time + "";
	}

	//
	// private static Date getNowDate() {
	// return Calendar.getInstance().getTime();
	// }

	public static String parseforDB(Date d) {
		return new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE).format(d);
	}

	public static String getAstro(String birthday) {
		// int[] days = {20,19,21,21,21,22,23,23,23,23,22,22};
		int month = 0;
		int day = 0;
		if (birthday != null && !"".equals(birthday)) {
			Date birth = parses(birthday);
			Calendar c = Calendar.getInstance();
			c.setTime(birth);
			month = c.get(Calendar.MONTH) + 1;
			day = c.get(Calendar.DAY_OF_MONTH);
		}
		// return astro.substring(month*2-(day < days[month] ? 2 : 0), 2);
		return getAstro(month, day);
	}

	private static String getAstro(int month, int day) {
		String[] astro = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座",
				"双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };
		int[] arr = new int[] { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };// 两个星座分割日
		int index = month;
		// 所查询日期在分割日之前，索引-1，否则不变
		if (day < arr[month - 1]) {
			index = index - 1;
		}
		// 返回索引指向的星座string
		return astro[index];
	}

	public void testCalender() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(2111222221);
		System.out.println(parseforDB(c.getTime()));
	}

	private long getMillisec(String str)
	{
		long millisecond = 0;
		try
		{
			millisecond = new SimpleDateFormat("yyyyMMddhhMMss").parse(str)
					.getTime();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return millisecond;
	}
}
