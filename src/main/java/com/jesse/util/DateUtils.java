package com.jesse.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 日期工具类
 *
 */
public class DateUtils {

	//public final static String LONG_DATE_FORMAT="yyyy-MM-dd HH:mm:ss:SSS";

	public final static String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";

	public final static String DATE_FORMAT="yyyy-MM-dd";

	private String dateString;

	public DateUtils(){
		dateString = new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date());
	}
	public static final long DATE_BASE = 946828800000l;// 基础时间 取 2000-01-03
	// 00:00:00 星期1

	private static final String DATE_FORMAR_STRING = "yyyy-MM-dd";// 时间格式化字符串
	private static final String DATE_MM_DD_FORMAR_STRING = "MM月dd日";// 时间格式化字符串
	private static final String DATETIME_FORMAR_STRING = "yyyy-MM-dd HH:mm:ss";// 时间格式化字符串
	private static final String DATETIME_FORMAR_STRING1 = "yyyy-MM-dd HH:mm";// 时间格式化字符串
	private static final String TIME_FORMAR_STRING = "HH:mm:ss";// 时间格式化字符串
	private static final String DATA_NOLINE_STRING = "yyyyMMdd";// 日期格式化字符串
	private static final String DATA_TIME_NOLINE_STRING = "yyMMddHHmmssSSS";// 时间格式化字符串
	private static final String DATE_YEAL_MM_DD_STRING = "yyyy年MM月dd日HH:mm";// 时间格式化字符串
	private static final String DATE_YEAL_MM_DD_STRING_MM = "yyyyMMddHHmmss";// 时间格式化字符串


	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAR_STRING);
	private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(DATETIME_FORMAR_STRING);
	private static final SimpleDateFormat datetimeFormat1 = new SimpleDateFormat(DATETIME_FORMAR_STRING1);
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAR_STRING);
	private static final SimpleDateFormat timeFormatMD = new SimpleDateFormat(DATE_MM_DD_FORMAR_STRING);
	private static final SimpleDateFormat dateNoLineFormat = new SimpleDateFormat(DATA_NOLINE_STRING);
	private static final SimpleDateFormat dateTimeNoLineFormat = new SimpleDateFormat(DATA_TIME_NOLINE_STRING);
	private static final SimpleDateFormat dateFormatYMD = new SimpleDateFormat(DATE_YEAL_MM_DD_STRING);
	private static final SimpleDateFormat dateFormatYMDDMS = new SimpleDateFormat(DATE_YEAL_MM_DD_STRING_MM);
	/**
	 * yyyy年MM月dd日HH:mm
	 * @param date
	 * @return String
	 */
	public static String formatDateYMD(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateFormatYMD.format(date);
		}
	}
	/**
	 * yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatNoLineDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateNoLineFormat.format(date);
		}
	}
	/**
	 * yyMMddHHmmss
	 * @param date
	 * @return String
	 */
	public static String formatNoLineDateTime(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateTimeNoLineFormat.format(date);
		}
	}
	/**
	 * yyyy-MM-dd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		} else {
			return dateFormat.format(date);
		}
	}
	/**
	 * yyyy-MM-dd
	 * @param calendar
	 * @return String
	 */
	public static String formatCalendar(Calendar calendar) {
		if (calendar == null) {
			return "";
		} else {
			return dateFormat.format(calendar.getTime());
		}
	}
	/**
	 * MM月dd日
	 * @param date
	 * @return String
	 */
	public static String formatDateMD(Date date) {
		if (date == null) {
			return "";
		} else {
			return timeFormatMD.format(date);
		}
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return String
	 */
	public static String formatDateTime(Date date) {
		if (date == null) {
			return "";
		} else {
			return datetimeFormat.format(date);
		}
	}
	/**
	 * HH:mm:ss
	 * @param date
	 * @return String
	 */
	public static String formatTime(Date date) {
		if (date == null) {
			return "";
		} else {
			return timeFormat.format(date);
		}
	}
	/**
	 * 时间转换
	 * @param date 时间
	 * @param format 格式
	 * @return String
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(date);
		}

	}

	public static Date parseDT(String dateString) throws ParseException {
		if(StringUtils.isEmpty(dateString)){
			return null;
		}
		return dateFormatYMDDMS.parse(dateString);
	}

	/**
	 * 字符串转换成时间类型
	 *转换后的时间格式  yyyy-MM-dd
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDate(String dateString) throws ParseException {
		if(StringUtils.isEmpty(dateString)){
			return null;
		}
		return dateFormat.parse(dateString);
	}
	/**
	 * 字符串转换成时间类型
	 *转换后的时间格式  HH:mm:ss
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseTime(String dateString) throws ParseException {
		return timeFormat.parse(dateString);
	}
	/**
	 *  字符串转换成时间类型
	 *转换后的时间格式  yyyy-MM-dd HH:mm:ss
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDateTime(String dateString) throws ParseException {
		return datetimeFormat.parse(dateString);
	}

	/**
	 *  字符串转换成时间类型
	 *转换后的时间格式  yyyy-MM-dd HH:mm
	 * @param dateString
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parseDateTime1(String dateString) throws ParseException {
		return datetimeFormat1.parse(dateString);
	}
	/**
	 *  字符串转换成时间类型
	 * @param dateString 时间
	 * @param format 格式
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String dateString, String format)
			throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateString);
	}

	/**
	 * 通过传入时间与当前时间比较，获得时间差值形成文字
	 *
	 * @param date
	 *            传入时间
	 * @return 串
	 */
	public static String getStringForDate(Date date) {

		String result = "";
		Calendar inputdate = Calendar.getInstance();
		inputdate.setTime(date);
		Calendar now = Calendar.getInstance();

		if (now.get(Calendar.YEAR) == inputdate.get(Calendar.YEAR)
				&& now.get(Calendar.MONTH) == inputdate.get(Calendar.MONTH)) {
			int day = now.get(Calendar.DATE) - inputdate.get(Calendar.DATE);
			switch (day) {
				case 0:
					break;
				case 1:
					result = "昨天";
					break;
				default:
					result = format(inputdate.getTime(), "yyyy年MM月dd日");
					break;
			}
			result = result + format(inputdate.getTime(), "HH:mm");
		} else {
			result = format(inputdate.getTime(), "yyyy年MM月dd日HH:mm");

		}

		return result;
	}

	/**
	 * 通过传入时间与当前时间比较，获得时间差值形成文字(简易)
	 *
	 * @param date
	 *            传入时间
	 * @return String
	 */
	public static String getSimpleStringForDate(Date date) {
		String result = "";
		Calendar inputdate = Calendar.getInstance();
		inputdate.setTime(date);
		Calendar now = Calendar.getInstance();

		int day = now.get(Calendar.DATE) - inputdate.get(Calendar.DATE);
		switch (day) {
			case 0:
				result = format(inputdate.getTime(), "HH:mm");
				break;
			default:
				result = format(inputdate.getTime(), "yyyy-MM-dd");
				break;
		}

		return result;
	}

	/**
	 * 获得与当前系统时间的相差天数
	 *
	 * @param date
	 * @return 相差天数 如果传入时间大于当前系统时间为负数
	 */
	public static long compareDate(long date) {
		long result = 0;
		long now = System.currentTimeMillis() - DateUtils.DATE_BASE;// 系统时间 -
		// 基数时间
		long inputdate = date - DateUtils.DATE_BASE;// 最后天数时间

		long day = 1000 * 3600 * 24;
		result = now / day - inputdate / day;
		return result;

	}

	/**
	 * 获得传入两个时间的相差天数
	 * @return 相差天数
	 */
	public static long compareDate(long startdate,long enddate) {
		long result = 0;
		long starttime = startdate - DateUtils.DATE_BASE;// 系统时间 -
		long endtime = enddate - DateUtils.DATE_BASE;// 最后天数时间

		long day = 1000 * 3600 * 24;
		result = endtime / day - starttime / day;
		return result;

	}

	/**
	 * 获得与当前系统时间的相差周数
	 *
	 * @param date
	 * @return 相差周数 如果传入时间大于当前系统时间为负数
	 */
	public static long compareWeek(long date) {
		long result = 0;
		long now = System.currentTimeMillis() - DateUtils.DATE_BASE;// 系统时间 -
		// 基数时间
		long inputdate = date - DateUtils.DATE_BASE;// 最后天数时间

		long week = 1000 * 3600 * 24 * 7;
		result = now / week - inputdate / week;
		return result;
	}

	/**
	 * 获得与当前系统时间的相差月数
	 *
	 * @param date
	 * @return 相差月数 如果传入时间大于当前系统时间为负数
	 *
	 * @author 刘宇
	 * @date 2011-05-18
	 */
	public static long compareMonth(long date) {
		Calendar now =Calendar.getInstance();
		Calendar input=Calendar.getInstance();
		input.setTimeInMillis(date);
		int yearnow=now.get(Calendar.YEAR);
		int monthnow=now.get(Calendar.MONTH);
		int yearinput=input.get(Calendar.YEAR);
		int monthinput=input.get(Calendar.MONTH);
		return yearnow*12+monthnow-yearinput*12-monthinput;

	}

	/**
	 * 计算年龄
	 *
	 * @param date
	 *            出生日期
	 * @return
	 */
	public static int calcAge(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar now = Calendar.getInstance();
		int age = now.get(Calendar.YEAR);
		now.setTime(date);
		return age - now.get(Calendar.YEAR);
	}

	/**
	 * 根据传入时间 获得本周第一天（周一)的时间
	 *
	 * @param date
	 * @return 返回时间 时分秒毫秒为0
	 */
	public static long getFristDayForWeek(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int tempday = calendar.get(Calendar.DAY_OF_WEEK);
		switch (tempday) {
			case 1:
				// 星期天
				calendar.add(Calendar.DATE, -6);
				break;
			default:
				// 周一到周六
				calendar.add(Calendar.DATE, -(tempday - 2));
				break;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/**
	 * 根据传入时间 获得本周第一天（周一)的时间
	 *
	 * @return 返回时间 时分秒毫秒为0
	 */
	public static long getFristDayForWeek() {
		return getFristDayForWeek(System.currentTimeMillis());
	}

	/**
	 * 根据传入时间 获得本周最后一天（周日)的时间
	 *
	 * @param date
	 * @return 返回时间 时分秒毫秒为23:59:59 999
	 */
	public static long getLastDayForWeek(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		int tempday = calendar.get(Calendar.DAY_OF_WEEK);
		switch (tempday) {
			case 1:
				// 星期天
				break;
			default:
				// 周一到周六
				calendar.add(Calendar.DATE, 8 - tempday);
				break;
		}
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime().getTime();
	}

	/**
	 * 根据传入时间 获得本周最后一天（周日)的时间
	 *
	 * @return 返回时间 时分秒毫秒为23:59:59 999
	 */
	public static long getLastDayForWeek() {
		return getLastDayForWeek(System.currentTimeMillis());
	}

	/**
	 * 根据传入时间 获得此时间所在月的 第一天的日期
	 * @param date
	 * @return
	 */
	public static Date getMonthFristDay(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND , calendar.getActualMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 根据传入时间 获得此时间所在月的 最后一天的日期
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay (long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND , calendar.getActualMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 根据传入时间 获得此时间所在年的 第一天的日期
	 * @param date
	 * @return
	 */
	public static Date getYearFristDay(long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND , calendar.getActualMinimum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 根据传入时间 获得此时间所在年的 最后一天的日期
	 * @param date
	 * @return
	 */
	public static Date getYearLastDay (long date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
		calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		calendar.set(Calendar.MILLISECOND , calendar.getActualMaximum(Calendar.MILLISECOND));
		return calendar.getTime();
	}

	/**
	 * 返回查询的开始时间
	 * @param date 查询时间
	 * @return
	 */
	public static String getBeginTime(Date date){
		return date==null?"2011-12-01 00:00:00":datetimeFormat.format(date);
	}

	/**
	 * 返回查询的结束时间
	 * @param date 查询时间
	 * @return
	 */
	public static String getEndTime(Date date){
		return date==null?datetimeFormat.format(System.currentTimeMillis()):datetimeFormat.format(date);
	}
	/**
	 * 根据指定格式，解释字符串，生成日期对象
	 * @param src　日期字符串
	 * @param format　格式化字符串
	 * @return　不能转化时返回Null
	 */
	public  static Date parseDate(String src,String format){
		Date date=null;
		if(src==null || src.equals(""))
			return null;
		try{
			date=new SimpleDateFormat(format).parse(src);
		}catch(Exception e){
			return null;
		}
		return date;
	}

	/**
	 * 把传入的日期，按指定格式返回其字符串形式
	 * @param d　日期对象
	 * @param format　格式字符串
	 * @return　不能转换返回Null
	 */
	public static String dateToString(Date d,String format){
		String str = null;
		if(d==null){
			return null;
		}
		else{
			try {
				str = new SimpleDateFormat(format).format(d);
			} catch (Exception e) {
				return null;
			}
		}
		return str;
	}

	/**
	 * 获取当前时间yyyy-MM-dd HH:mm:ss字符串形式
	 * @return
	 */
	public  static String getCurrentDate() {
		return getCurrentDate(DATE_TIME_FORMAT);
	}

	/**
	 * 获取当前时间指定格式的字符串形式
	 * 格式错误按yyyy-MM-dd HH:mm:ss
	 * @param sFormat:日期格式字符串
	 * @return String　转换成指定格式的字符串形式
	 */
	public  static String getCurrentDate(String sFormat) {
		if (sFormat == null || sFormat.equals(""))
			sFormat = DATE_TIME_FORMAT;
		SimpleDateFormat formatter = new SimpleDateFormat(sFormat);
		return formatter.format(new java.util.Date());
	}

	/**
	 * 计算时间差：天 /小时/分/秒
	 * @param beginTime  开始时间
	 * @param endTime    结束时间
	 * @param type       时间差类型：1天，2小时，3分，4秒
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeLength(String beginTime,String endTime,String type) throws ParseException{

		String timeLength = "";
		Date begin = parseDate(beginTime, DATE_TIME_FORMAT);
		Date end = parseDate(endTime, DATE_TIME_FORMAT);
		long between=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒
		if("1".equals(type)){
			long day = between/(24*3600);
			timeLength = day+"";
		}else if("2".equals(type)){
			long hour = between%(24*3600)/3600;
			timeLength = hour+"";
		}else if("3".equals(type)){
			long minute = between%3600/60;;
			timeLength = minute+"";
		}else if("4".equals(type)){
			long second = between%60/60;
			timeLength = second+"";
		}
		return timeLength;
	}

	/**
	 * 返回年份数值
	 * @return
	 */
	public int getYear() {
		return Integer.parseInt(this.dateString.substring(0, 4));
	}
	/**
	 * 返回4位年份字符串
	 * @return
	 */
	public String getStrYear() {
		return this.dateString.substring(0, 4);
	}

	/**
	 * 返回月份数值
	 * @return int
	 */
	public int getMonth() {
		return Integer.parseInt(this.dateString.substring(5,7));
	}

	/**
	 * 返回2位月份字符串
	 * @return string
	 */
	public String getStrMonth() {
		return this.dateString.substring(5,7);
	}

	/**
	 * 返回日数值
	 * @return int
	 */
	public int getDay() {
		return Integer.parseInt(this.dateString.substring(8,10));
	}
	/**
	 * 返回2位日字符串
	 * @return String
	 */
	public String getStrDay() {
		return this.dateString.substring(8,10);
	}

	/**
	 * 返回小时值
	 * @return int
	 */
	public int getHour() {
		return Integer.parseInt(this.dateString.substring(11,13));
	}

	/**
	 * 返回2位小时字符串
	 * @return String
	 */
	public String getStrHour() {
		return this.dateString.substring(11,13);
	}

	/**
	 * 返回分钟值
	 * @return int
	 */
	public int getMinute() {
		return Integer.parseInt(this.dateString.substring(10, 12));
	}

	/**
	 * 返回2位分钟字符串
	 * @return String
	 */
	public String getStrMinute() {
		return this.dateString.substring(14,16);
	}

	/**
	 * 返回秒值
	 * @return int
	 */
	public int getSecond() {
		return Integer.parseInt(this.dateString.substring(14,16));
	}
	/**
	 * 返回2位秒字符串
	 * @return String
	 */
	public String getStrSecond() {
		return this.dateString.substring(17,19);
	}

	/**
	 * 返回年月日字符串格式如　1997-07-01
	 * @return Sting
	 */
	public String getStrDate() {
		return this.dateString.substring(0,10);
	}

	/**
	 * 返回时分秒字符串　01:01:01
	 * @return Sting
	 */
	public String getStrTime() {
		return this.dateString.substring(12,19);
	}
	
	public static String getStringDate(String value) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date d = sdf.parse(value);
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	 /*

    获取当前时间之前或之后几小时 hour

   */

    public static String getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }

    /*

    获取当前时间之前或之后几分钟 minute

    */

    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }

	
}
