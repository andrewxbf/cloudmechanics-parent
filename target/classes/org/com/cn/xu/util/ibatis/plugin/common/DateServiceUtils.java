package org.com.cn.xu.util.ibatis.plugin.common;



import org.com.cn.xu.util.Loggers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * @Title: DateServiceUtils.java
 * @Package com.framework.utils
 * @Description:
 * @author maxwell
 * @version V1.0
 * @date
 */
public class DateServiceUtils {

	private static final Loggers loggers = Loggers.getLogger(DateServiceUtils.class);

	private static IDateService dateService;
	/**
	 * 注入日期获取器
	 * @param ds 必须实现com.framework.core.commons.IDateService接口， 如果为null值，将默认使用com.framework.core.commons.DefaultDateService
	 */
	public static void registDateService(IDateService ds){
		dateService = ds;
	}
	/**
	 * 获取系统日期时间读取器
	 * @return java对象，日期读取对象
	 */
	private static IDateService getDateService(){
		if(dateService == null){
			dateService = new DefaultDateService();
		}
		return dateService;
	}
	/**
	 * 获取系统日期时间
	 * @return java日期对象
	 */
	public static Date getCurrentDate(){
		return getDateService().getCurrentDate();
	}
	/**
	 * 格式日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return 日期字符串
	 */
	public static String formatDate(Date date,String pattern){
		if(date != null){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.format(date);
			}catch(Exception e){
//                ex.printStackTrace();
				loggers.error(e.getMessage());
			}
		}
		return null;
	}
	/**
	 * 字符串转化为日期
	 * @param date 日期
	 * @param pattern 日期格式
	 * @return java日期对象
	 */
	public static Date parseDate(String date,String pattern){
		if(date != null && date.length()>0){
			try{
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(date);
			}catch(Exception e){
//                ex.printStackTrace();
				loggers.error(e.getMessage());
			}
		}
		return null;
	}
	/**
	 * 将带时间的日期格式化至当天23:59:59秒
	 * @param date 日期
	 * @return 日期对象
	 */
	public static Date endingDate(Date date){
		if(date == null) {
            return null;
        }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	/**
	 * 将带时间的日期格式化至当天00:00:00秒
	 * @param date 日期
	 * @return 日期对象
	 */
	public static Date beginningDate(Date date){
		if(date == null) {
            return null;
        }
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day,00,00,00);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

}
