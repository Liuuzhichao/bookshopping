package cn.sdut.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转化的工具类
 * @author Administrator
 *
 */
public class Dateutils {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String FormatDate(Date date) {
		return sdf.format(date);
	}
	
}
