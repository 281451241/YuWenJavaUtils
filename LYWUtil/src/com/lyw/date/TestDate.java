package com.lyw.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestDate {

	
	private static SimpleDateFormat forDB=new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
	
	private static Date getNowDate() {
		return Calendar.getInstance().getTime();
	}
	
	public static String parseforDB(Date d){
		String str = forDB.format(d);
		return str;
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
	
	public static String getAstro(String birthday) {
//		int[] days = {20,19,21,21,21,22,23,23,23,23,22,22};
		int month = 0;
		int day = 0;
		if (birthday != null && !"".equals(birthday)) {
			Date birth = parses(birthday);
			Calendar c = Calendar.getInstance();
			c.setTime(birth);
			month = c.get(Calendar.MONTH) + 1;
			day = c.get(Calendar.DAY_OF_MONTH);
		}
//		return astro.substring(month*2-(day < days[month] ? 2 : 0), 2);
		return getAstro(month,day);
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
	
	public static void main(String[] args) {
//		TestDate td = new TestDate();
//		System.out.println(td.parseforDB(getNowDate()));
		
		System.out.println(TestDate.getAstro("2013-02-27"));
		
//		System.out.println(td.getAstro(2, 29));
	}
}
