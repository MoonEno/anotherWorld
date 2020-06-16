package waw.anotherworld.demon.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Class Name : UtilDate.java
 * Description : UtilDate class
 * Modification Information
 *
 * @author Wan
 * @since 2015. 6. 8.
 * @version 1.0
 *
 */
public class UtilDate {

		public final static String FORMAT_YYYYMMDD = "yyyyMMdd";
		public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
		public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(FORMAT_YYYYMMDD);
		public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(FORMAT_YYYY_MM_DD);

		final static String[] lDays = {"31", "29", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};
		final static String[] sDays = {"31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"};

		public final static int SUNDAY = Calendar.SUNDAY;
		public final static int MONDAY = Calendar.MONDAY;
		public final static int TUESDAY = Calendar.TUESDAY;
		public final static int WEDNESDAY = Calendar.WEDNESDAY;
		public final static int THURSDAY = Calendar.THURSDAY;
		public final static int FRIDAY = Calendar.FRIDAY;
		public final static int SATURDAY = Calendar.SATURDAY;

		/**
		 * Getting Current Date String
		 * @param format
		 * @return String
		 */
		public static String getCurrent(String format){
			return new SimpleDateFormat(format, Locale.getDefault()).format(new Date());
		}

		/**
		 * Getting Current Date String
		 * @param format
		 * @param locale
		 * @return String
		 */
		public static String getCurrent(String format, Locale locale){
			return new SimpleDateFormat(format, locale).format(new Date());
		}

		// 한달 후 날짜 구하기
		public static String getAfterMonth() {
			Calendar cal = Calendar.getInstance();
			cal.add(cal.MONTH, + 1);
			return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}

		// 2주후 날짜 구하기
		public static String getAfterWeek() {
			Calendar cal = Calendar.getInstance();
			cal.add(cal.WEEK_OF_MONTH, + 2);
			return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		}


		/**
		 * Getting Current Date String (yyyyMMdd)
		 * @return String
		 */
		public static String getCurrentDate(){
			return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
		}
		
		/**
		 * Getting Current Time (HHmmss)
		 * @return String
		 */
		public static String getCurrentTime(){
			return new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
		}

		/**
		 * Getting Current Time (HHmmss)
		 * @return String
		 */
		public static String getCurrentDateTime(){
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
		}

		/**
		 * Getting Current Time 24 (HH:mm:ss)
		 * @param locale
		 * @return String
		 */
		public static String getCurrentTime24(Locale locale){
			return getCurrent("HH:mm:ss", locale);
		}

		/**
		 * Getting Current Time 12 (HH:mm:ss)
		 * @param locale
		 * @return String
		 */
		public static String getCurrentTime12(Locale locale){
			return getCurrent("hh:mm:ss", locale);
		}

		/**
		 * Getting Current Year
		 * @return int
		 */
		public static int getCurrentYear(){
			return Integer.parseInt(getCurrentYearString());
		}

		/**
		 * Getting Current Month
		 * @return int
		 */
		public static int getCurrentMonth(){
			return Integer.parseInt(getCurrentMonthString());
		}

		/**
		 * Getting Current Day
		 * @return int
		 */
		public static int getCurrentDay(){
			return Integer.parseInt(getCurrentDayString());
		}

		/**
		 * Getting Current Hour
		 * @return int
		 */
		public static int getCurrentHour(){
			return Integer.parseInt(getCurrent("HH"));
		}

		/**
		 * Getting Current Minute
		 * @return int
		 */
		public static int getCurrentMinute(){
			return Integer.parseInt(getCurrent("mm"));
		}

		/**
		 * Getting Current Year String
		 * @return String
		 */
		public static String getCurrentYearString(){
			return getCurrent("yyyy");
		}

		/**
		 * Getting Current Month String
		 * @return String
		 */
		public static String getCurrentMonthString(){
			return getCurrent("MM");
		}

		/**
		 * Getting Current Year and Month String
		 * @return String
		 */
		public static String getCurrentYearMonthString(){
			return getCurrent("yyyyMM");
		}

		/**
		 * Getting Current Day String
		 * @return String
		 */
		public static String getCurrentDayString(){
			return getCurrent("dd");
		}

		/**
		 * Getting Current Month and Day String
		 * @return String
		 */
		public static String getMonthDayString(){
			return getCurrent("MMdd");
		}

		/**
		 * 현재의 분기를 리턴한다. (1:1분기, 2:2분기, 3:4분기, 4:4분기)
		 * @return int
		 */
		public static int getCurrentQuarter(){
			int currentMonth = getCurrentMonth();
			if(currentMonth < 4)
				return 1;
			else if(currentMonth < 7)
				return 2;
			else if(currentMonth < 10)
				return 3;
			else
				return 4;
		}

		/**
		 * 현재의 반기를 리턴한다. (1:상반기, 2:하반기)
		 * @return int
		 */
		public static int getCurrentHalf(){
			int currentMonth = getCurrentMonth();
			if(currentMonth >= 6)
				return 1;
			return 2;
		}

		/**
		 * 해당일의 월첫날을 리턴한다.
		 * @return Date
		 */
		public static Date getFirstDayOfMonth(Date sdate){
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdate);
			return getFirstDayOf(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
		}
		
		/**
		 * 금월의 첫날을 리턴한다.
		 * @return Date
		 */
		public static Date getFirstDayOfCurrentMonth(){
			String dateStr = null;
			int month = getCurrentMonth();
			if(month < 10){
				dateStr = getCurrentYearString() + "0" + month + "01";
			}else{
				dateStr = getCurrentYearString() + month + "01";
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 금월의 마지막날을 리턴한다.
		 * @return Date
		 */
		public static Date getLastDayOfCurrentMonth(){
			String dateStr = null;
			if(getCurrentYear() % 4 == 0){
				dateStr = lDays[getCurrentMonth() -1];
			}else{
				dateStr = sDays[getCurrentMonth() - 1];
			}
			try {
				return yyyyMMdd.parse(UtilDate.getCurrent("yyyyMM") + dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

	/**
	 * 다음월의 마지막날을 리턴한다.
	 * @return Date
	 */
	public static String getMonthAfterLastDayOfCurrentMonth(){
		String dateStr = null;
		if(getCurrentYear() % 4 == 0){
			dateStr = lDays[getCurrentMonth() + 1];
		}else{
			dateStr = sDays[getCurrentMonth() + 1];
		}
		System.out.println(dateStr);
		try {
			Date afterDate = yyyy_MM_dd.parse(UtilDate.getCurrent("yyyy-MM-") + dateStr);
			return new SimpleDateFormat("yyyy-MM-dd").format(afterDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

		/**
		 * 입력된 년월의 첫날로 변환하여 리턴한다.
		 * @param yyyyMM
		 * @return String
		 */
		public static String toFirstDay(String yyyyMM){
			Date firstDate = getFirstDayOf(yyyyMM.substring(0, 4), yyyyMM.substring(4, 6));
			return new SimpleDateFormat("yyyy-MM-dd").format(firstDate);
		}

		/**
		 * 입력된 년월의 첫날을 리턴한다.
		 * @param year
		 * @param month
		 * @return Date
		 */
		public static Date getFirstDayOf(String year, String month){
			return getFirstDayOf(Integer.parseInt(year), Integer.parseInt(month));
		}

		/**
		 * 입력된 년월의 첫날을 리턴한다.
		 * @param year
		 * @param month
		 * @return Date
		 */
		public static Date getFirstDayOf(int year, int month){
			String dateStr = null;
			if(month < 10){
				dateStr = year + "0" + month + "01";
			}else{
				dateStr = year + "" + month + "01";
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 입력된 년월의 마지막날로 변환하여 리턴한다.
		 * @param yyyyMM
		 * @return String
		 */
		public static String toLastDay(String yyyyMM){
			Date lastDate = getLastDayOf(yyyyMM.substring(0, 4), yyyyMM.substring(4, 6));
			return new SimpleDateFormat("yyyy-MM-dd").format(lastDate);
		}

		/**
		 * 입력된 년월의 마지막날을 리턴한다.
		 * @param year
		 * @param month
		 * @return Date
		 */
		public static Date getLastDayOf(String year, String month){
			return getLastDayOf(Integer.parseInt(year), Integer.parseInt(month));
		}
		
		/**
		 * 입력된 년월의 마지막날을 리턴한다.
		 * @param date
		 * @return Date
		 */
		public static Date getLastDayOfMonth(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			return cal.getTime();
		}

		/**
		 * 입력된 년월의 마지막날을 리턴한다.
		 * @param year
		 * @param month
		 * @return Date
		 */
		public static Date getLastDayOf(int year, int month){
			String dateStr = null;
			if(month < 10){
				dateStr = year + "0" + month;
			}else{
				dateStr = year + "" + month;
			}
			if(year % 4 == 0){
				dateStr = dateStr + lDays[month -1];
			}else{
				dateStr = dateStr + sDays[month - 1];
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 입력된 년도의 첫날을 리턴한다.
		 * @param year
		 * @return Date
		 */
		public static Date getFirstDayOfYear(String year){
			try {
				return yyyyMMdd.parse(year + "0101");
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 입력된 년도의 마지막날을 리턴한다.
		 * @param year
		 * @return Date
		 */
		public static Date getLastDayOfYear(String year){
			try {
				return yyyyMMdd.parse(year + "1231");
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 분기별 첫날을 리턴한다.
		 * @param quarter
		 * @return Date
		 */
		public static Date getFirstDayOfQuarter(int quarter){
			String dateStr = null;
			switch (quarter) {
				case 1 :
					dateStr = getCurrentYearString() + "0101";
					break;
				case 2 :
					dateStr = getCurrentYearString() + "0401";
					break;
				case 3 :
					dateStr = getCurrentYearString() + "0701";
					break;
				default :
					dateStr = getCurrentYearString() + "1001";
					break;
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 지정된 년도의 분기별 첫날을 리턴한다.
		 * @param year
		 * @param quarter
		 * @return Date
		 */
		public static Date getFirstDayOfQuarter(String year, int quarter){
			String dateStr = null;
			switch (quarter) {
			case 1 :
				dateStr = year + "0101";
				break;
			case 2 :
				dateStr = year + "0401";
				break;
			case 3 :
				dateStr = year + "0701";
				break;
			default :
				dateStr = year + "1001";
				break;
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 분기별 마지막 날짜를 리턴한다.
		 * @param quarter
		 * @return Date
		 */
		public static Date getLastDayOfQuarter(int quarter){
			String dateStr = null;
			switch (quarter) {
				case 1 :
					dateStr = getCurrentYearString() + "0331";
					break;
				case 2 :
					dateStr = getCurrentYearString() + "0630";
					break;
				case 3 :
					dateStr = getCurrentYearString() + "0930";
					break;
				default :
					dateStr = getCurrentYearString() + "1231";
					break;
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 지정된 년도의 분기별 마지막 날짜를 리턴한다.
		 * @param year
		 * @param quarter
		 * @return Date
		 */
		public static Date getLastDayOfQuarter(String year, int quarter){
			String dateStr = null;
			switch (quarter) {
			case 1 :
				dateStr = year + "0331";
				break;
			case 2 :
				dateStr = year + "0630";
				break;
			case 3 :
				dateStr = year + "0930";
				break;
			default :
				dateStr = year + "1231";
				break;
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 입력된 날짜가 속한 주의 week 기준 요일의 날짜를 리턴
		 * 		ex) 2008-12-11 일의 MONDAY(일요일)를 입력하면 2008-12-08 일이 리턴됨.
		 * @param week (1:일요일, 2:월요일, 3:화요일 ...7:토요일)
		 * @param date
		 * @return Date
		 */
		public static Date getDayOfWeek(int week, Date date){
	        Calendar todayCalendar = Calendar.getInstance();
	        todayCalendar.setTime(date);

	        int dayOfWeek = todayCalendar.get(Calendar.DAY_OF_WEEK);
			String today = yyyyMMdd.format(date);

			String dateStr = null;
			if(dayOfWeek == week){
				dateStr = today;
			}else{
				dateStr = moveDay(today, ((dayOfWeek - (week+1)) * (-1)));
			}
			try {
				return yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 입력된 날짜가 속한 주의 week 기준 요일의 날짜를 리턴
		 * 		ex) 2008-12-11 일의 MONDAY(일요일)를 입력하면 2008-12-08 일이 리턴됨.
		 * @param week (1:일요일, 2:월요일, 3:화요일 ...7:토요일)
		 * @param timeMillis
		 * @return Date
		 */
		public static Date getDayOfWeek(int week, long timeMillis){
			Date date = new Date(timeMillis);
			return getDayOfWeek(week, date);
		}
		
		
		/**
		 * 입력된 날짜의 요일을 리턴
		 * @param week (1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일)
		 * @param timeMillis
		 * @return Date
		 */
		public static int getDayOfWeek(Date date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_WEEK);
		}
		
		/**
		 * 입력된 날짜의 요일을 리턴
		 * @param week (1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일)
		 * @param timeMillis
		 * @return Date
		 */
		public static int getDayOfWeek(String date){
			Calendar cal = Calendar.getInstance();
			cal.setTime(UtilDate.getStringToDate(date,"yyyy-MM-dd"));
			return cal.get(Calendar.DAY_OF_WEEK);
		}
		
		
		
		/**
		 * 오늘 날짜의 요일을 리턴
		 * @param week (1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일)
		 * @param timeMillis
		 * @return Date
		 */
		public static int getDayOfWeekToday(){
			Calendar cal = Calendar.getInstance();
			return cal.get(Calendar.DAY_OF_WEEK);
		}
		
		
		/**
		 * 입력한 숫자의 요일을 문자로 반환 
		 * @param week (1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일)
		 * @return
		 */
		public static String getDayOfWeekString(int week) {
			String returnValue = "";
			
			switch (week) {
				case 1: returnValue = "일";	break;
				case 2: returnValue = "월";	break;
				case 3: returnValue = "화";	break;
				case 4: returnValue = "수";	break;
				case 5: returnValue = "목";	break;
				case 6: returnValue = "금";	break;
				case 7: returnValue = "토";	break;
				default:
					break;
			}
			
			return returnValue;
		}		
		
		

		/**
		 * 윤년인지 여부 리턴
		 * @param year
		 * @return boolean
		 */
		public static boolean isLunarYear(int year){
			return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
		}

		public static final String COMPARE_TYPE_DAY = "day";
		public static final String COMPARE_TYPE_HOUR = "hour";
		public static final String COMPARE_TYPE_MINUTE = "minute";
		public static final String COMPARE_TYPE_SECOND = "second";

		/**
		 * 현재 날짜(시간)와 입력한 날짜(시간)와의 차이
		 * @param date - 20050707120202
		 * @param type - day, hour, minute, second
		 * @return long
		 */
		public static long compareThisDate(String date, String type) throws Exception {
			if(date == null)
				throw new NullPointerException("UtilDate.compareThisDate : Date is Null");

			date = date.replaceAll("\\/", "");
			date = date.replaceAll("\\-", "");
			date = date.replaceAll("\\:", "");
			date = date.replaceAll("\\.", "");

			int len = date.length();
			long rvGap = 0;

			Calendar cal1= Calendar.getInstance();
			Calendar cal2= Calendar.getInstance();

			int year = 0;
			int month = 0;
			int day = 0;
			int hour = 0;
			int minute = 0;
			int second = 0;

			if(len >= 4)
				year = Integer.parseInt(date.substring(0, 4));
			if(len >= 6)
				month = Integer.parseInt(date.substring(4, 6));
			if(len >= 8)
				day = Integer.parseInt(date.substring(6, 8));
			if(len >= 10)
				hour = Integer.parseInt(date.substring(8, 10));
			if(len >= 12)
				minute = Integer.parseInt(date.substring(10, 12));
			if(len >= 14)
				second = Integer.parseInt(date.substring(12, 14));

			cal1.set(year, month - 1, day, hour, minute, second);

			long cal1sec = cal1.getTime().getTime();
			long cal2sec = cal2.getTime().getTime();

			long gap = cal2sec - cal1sec;

			if (type.equals(COMPARE_TYPE_DAY)) rvGap = (gap / 86400) / 1000;
			else if (type.equals(COMPARE_TYPE_HOUR)) rvGap = (gap / 3600) / 1000;
			else if (type.equals(COMPARE_TYPE_MINUTE)) rvGap = (gap / 60) / 1000;
			else if (type.equals(COMPARE_TYPE_SECOND)) rvGap = gap / 1000;

			return rvGap;
		}

		/**
		 * @param date
		 * @return boolean
		 */
		public static boolean isAfterDay(String date) throws Exception {
			long r = compareThisDate(date, COMPARE_TYPE_DAY);
			if(r > 0){
				return true;
			}else{
				return false;
			}
		}

		/**
		 * @param date
		 * @return boolean
		 * @throws Exception
		 */
		public static boolean isAfterHour(String date) throws Exception {
			long r = compareThisDate(date, COMPARE_TYPE_HOUR);
			if(r > 0){
				return true;
			}else{
				return false;
			}
		}

		/**
		 * @param date
		 * @return boolean
		 */
		public static boolean isBeforeDay(String date)throws Exception {
			long r = compareThisDate(date, COMPARE_TYPE_DAY);
			if(r > 0){
				return false;
			}else{
				return true;
			}
		}

		/**
		 * @param date
		 * @return boolean
		 * @throws Exception
		 */
		public static boolean isBeforeHour(String date)throws Exception {
			long r = compareThisDate(date, COMPARE_TYPE_HOUR);
			if(r > 0){
				return false;
			}else{
				return true;
			}
		}

		/**
		 * 주어진 날짜의 주어진 년도 뒤의 날짜를 리턴한다.<BR>
		 * @param yyyyMMdd 주어진 날짜
		 * @param year 주어진 년도수( ex 1 이면 1년 후의 날짜를 리턴 -1이면 1년 이전의 날짜를 리턴)
		 * @return String 주어진 년도의 주어진 년도 수 뒤의 날짜
		 */
		public static String moveYear(String yyyyMMdd, int year){
			if(yyyyMMdd == null || !(yyyyMMdd.length() == 8 || yyyyMMdd.length() == 14)) {
				throw new RuntimeException(yyyyMMdd);
			}

			int iYear = Integer.parseInt(yyyyMMdd.substring(0, 4));
			iYear += year;
			return iYear + yyyyMMdd.substring(4);
		}

		/**
		 * 주어진 날짜의 주어진 개월 수 뒤의 날짜를 리턴한다.<BR>
		 * @param yyyyMMdd 주어진 날짜
		 * @param day 주어진 일수( ex 1 이면 1일 후의 날짜를 리턴 -1이면 1일 이전의 날짜를 리턴)
		 * @return String 주어진 날짜의 주어진 개월 수 뒤의 날짜
		 */
		public static String moveDay(String yyyyMMdd, int day){

			if(yyyyMMdd == null || !(yyyyMMdd.length() == 8 || yyyyMMdd.length() == 14)) {
				throw new RuntimeException(yyyyMMdd);
			}

			int iYear = Integer.parseInt(yyyyMMdd.substring(0, 4));
			int iMonth = Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1;  // 달은 0부터 시작 한다.
			int iDay = Integer.parseInt(yyyyMMdd.substring(6, 8));

			// 시간을 가져온다.
			int iHour = 0;
			int iMin = 0;
			int iSecond = 0;
			String pattern = "yyyyMMdd";
			if(yyyyMMdd.length() == 14) {
				iHour = Integer.parseInt(yyyyMMdd.substring(8, 10));
				iMin = Integer.parseInt(yyyyMMdd.substring(10, 12));
				iSecond = Integer.parseInt(yyyyMMdd.substring(12, 14));
				pattern = "yyyyMMddHHmmssSSS";
			}

			// Calendar객체를 생성하고 날짜와 시간을 설정한다.
			Calendar calCurDate = new GregorianCalendar();
			calCurDate.set(iYear, iMonth, iDay, iHour, iMin, iSecond);

			// 주어진 개월을 더한다.
			int iReturnMonth = calCurDate.get(Calendar.DATE) + day;

			// 달을 설정한다.
			calCurDate.set(Calendar.DATE, iReturnMonth);
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);

			String strResult = formatter.format(calCurDate.getTime());

			if(yyyyMMdd.length() == 8) {
				strResult = strResult.substring(0, 8);
			}

			return strResult;
		}

		/**
		 * 주어진 날짜의 주어진 개월 수 뒤의 날짜를 리턴한다.<BR>
		 * @param yyyyMMdd 주어진 날짜
		 * @param mon 주어진 개월수( ex 1 이면 1개월 후의 날짜를 리턴 -1이면 1개월 이전의 날짜를 리턴)
		 * @return String 주어진 날짜의 주어진 개월 수 뒤의 날짜
		 */
		public static String moveMonth(String yyyyMMdd, int mon) {
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				Date date = df.parse(yyyyMMdd);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.add(Calendar.MONTH, mon);
				date = c.getTime();
				return df.format(date);
			} catch (ParseException e) {
				throw new RuntimeException("문자열 날자 변환 실패" + e.getMessage());
			}
		}

		/**
		 *  현재 날짜의 일주일 뒤 날짜 리턴
		 * @return
		 */
		public static String sevenAfterDay() {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, +6);
			Date date = calendar.getTime();
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}

		/**
		 * 특정 일자 뒤의 날짜 리턴
		 * @param startDt, period
		 * @return
		 */
		public static String AfterDay(String startDt, String preiod) {
			try {
				int prd = Integer.parseInt(preiod) - 1;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dt = sdf.parse(startDt);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dt);
				calendar.add(Calendar.DAY_OF_MONTH, prd);
				dt = calendar.getTime();
				return sdf.format(dt);
			} catch (ParseException e) {
				throw new RuntimeException("문자열 날자 변환 실패" + e.getMessage());
			}
		}
		/**
		 * 두 날짜간의 차이 구하기... 일(day)단위 리턴
		 * @param a
		 * @param b
		 * @return long
		 */
		public static long subtractDays(Date a, Date b) {
			long gap = 0;
			gap = a.getTime() - b.getTime();
			gap = gap / 86400000; //24 * 60 * 60 * 1000
			return gap;
		}

		/**
		 * 두 날짜간의 차이 구하기... 일(day)단위 리턴(소수점까지 계산)
		 * @param a
		 * @param b
		 * @return long
		 */
		public static double subtractDaysDouble(Date a, Date b) {
			double gap = 0;
			gap = a.getTime() - b.getTime();
			gap = gap / 86400000.0; //24 * 60 * 60 * 1000
			return gap;
		}

		/**
		 * 두 날짜간의 차이 구하기... 월(month)단위 리턴
		 * @param a
		 * @param b
		 * @return long
		 */
		public static long subtractMonths(Date a, Date b) {
			long gap = 0;
			gap = a.getTime() - b.getTime();
			gap = gap / 2592000000L; //30 * 24 * 60 * 60 * 1000
			return gap;
		}

		/**
		 * 두 날짜간의 차이 구하기... 일(day)단위 리턴
		 * @param start
		 * @param end
		 * @param format
		 * @return long
		 */
		public static long subtractDays(String start, String end, String format) {
			long gap = 0;
			try {
				if( format == null)
					format = "yyyyMMdd";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date1 = sdf.parse(start);
				Date date2 = sdf.parse(end);
				gap = subtractDays(date1, date2);
				return gap;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 두 날짜간의 차이 구하기... 월(month)단위 리턴
		 * @param start
		 * @param end
		 * @param format
		 * @return long
		 */
		public static long subtractMonths(String start, String end, String format) {
			long gap = 0;
			try {
				if( format == null)
					format = "yyyyMM";
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				Date date1 = sdf.parse(start);
				Date date2 = sdf.parse(end);
				gap = subtractMonths(date1, date2);
				return gap;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		/**
		 * 두 날짜간의 차이 구하기...일(day)단위 리턴
		 * @param start
		 * @param end
		 * @return long
		 */
		public static long subtractDays(String start, String end) {
			return subtractDays(start, end, null);
		}

		/**
		 * 두 날짜간의 차이 구하기...월(month)단위 리턴
		 * @param start 6자리(yyyyMM)
		 * @param end 6자리(yyyyMM)
		 * @return long
		 */
		public static long subtractMonths(String start, String end) {
			return subtractMonths(start, end, null);
		}


		/**
		 * 제공된 기간내 지정한 요일의 일수를 구하기
		 * @param fromDate
		 * @param toDate
		 * @param weeks
		 * @return int
		 */
		public static int countDayOfWeek(Date fromDate, Date toDate, int[] weeks){
			int count = 0;

			Calendar c = Calendar.getInstance();
			c.setTime(fromDate);
			while(!c.getTime().equals(toDate)){
				for(int i = 0; i < weeks.length; i++){
					if(weeks[i] == c.get(Calendar.DAY_OF_WEEK)){
						count++;
						break;
					}
				}
				c.add(Calendar.DAY_OF_MONTH, 1);
			}

			return count;
		}

		/**
		 * 특정 일자 (yyyy-MM-dd, yyyyMMdd)의 값을 받아서 요일을 찾는다.
		 * week (1:일요일, 2:월요일, 3:화요일 ...7:토요일)
		 * @param date
		 * @return int
		 */
		public static int dayOfWeek(String date){
			
			date = date.replaceAll("-", "");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
			Calendar cal = Calendar.getInstance();
			int dayOfWeek = 0;

			try {
				cal.setTime(sdf.parse(date));
				dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			return dayOfWeek;
		}

		/**
		 * 입력된 문자열의 날짜가 유효한 날짜인지 여부 리턴
		 * @param date
		 * @param format
		 * @return boolean
		 */
		public static boolean isValidDate(String date, String format){
			if(date == null){
				return false;
			}
			date = date.replaceAll(" ", "");
			try {
				if(date.length() != format.length()){
					return false;
				}

				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.parse(date);
			} catch (ParseException e) {
				return false;
			}
			return true;
		}
		
		/**
		 * 입력한 Date 의 몇 주차인지를 리턴
		 * @param date
		 * @return int
		 */
		public static int getWeekOfYear(Date date) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.WEEK_OF_YEAR);
		}
		
		
		/**
		 * 오늘이 몇 주차인지를 리턴
		 * @return int
		 */
		public static int getTodayWeekOfYear() {
			
			return getWeekOfYear(new Date());
		}
		
		
		/**
		 * 입력한 Date 에 입력한 일,월,달 더하기 ( 빼기는 - 입력)
		 * 
		 * @param date
		 * @param dateType Calendar.YEAR 년,Calendar.MONTH 월, Calendar.DAY_OF_YEAR 일 , Calendar.HOUR_OF_DAY 시간, Calendar.MINUTE 분
		 * @param addNum
		 * @return Date
		 */
		public static Date getAddDate(Date date, int dateType , int addNum) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(dateType, addNum); 
			
			return cal.getTime();
		}
		
		/**
		 * 입력한 Date Type 값 반환
		 * 
		 * @param date
		 * @param dateType Calendar.YEAR 년,Calendar.MONTH 월, Calendar.DAY_OF_YEAR 일 , Calendar.HOUR_OF_DAY 시간, Calendar.MINUTE 분
		 * @return int
		 */
		public static int getDateType(Date date , int dateType) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(dateType);
		}
		
		/**
		 * 오늘 날짜에 입력한 일,월,달 더하기 ( 빼기는 - 입력)
		 * 
		 * @param dateType Calendar.YEAR 년,Calendar.MONTH 월, Calendar.DAY_OF_YEAR 일
		 * @param addNum
		 * @return Date
		 */
		public static Date getTodayAddDate(int dateType , int addNum) {
			return getAddDate(new Date(), dateType, addNum);
		}

		
		/**
		 * 입력한 날짜를 정해진 포맷으로 날자를 String 타입으로 리턴
		 * (yyyy-MM-dd HH:mm:ss)
		 * @return String
		 */
		public static String getDateTime(Date date){
			return getDateTime(date, "yyyy-MM-dd HH:mm:ss");
		}
		
		/**
		 * 입력한 날짜를 정해진 포맷으로 날자를 String 타입으로 리턴
		 * (yyyy-MM-dd)
		 * @return String
		 */
		public static String getDateTimeBasic(Date date){
			return getDateTime(date, "yyyy-MM-dd");
		}
		
		/**
		 * 입력한 날짜를 입력한 포맷으로 날자를 String 타입으로 리턴
		 * @return String
		 */
		public static String getDateTime(Date date, String format){
			return new SimpleDateFormat(format, Locale.getDefault()).format(date);
		}
		
		
		/**
		 *  입력한 날짜의 PHP 식 10자리의 milliseconds 
		 * @param date
		 * @return
		 */
		public static long getTime(Date date) {
			
			return date.getTime()/1000;
		}
		
		
		/**
		 *  입력한 날짜의 milliseconds (java) 
		 * @param date
		 * @return
		 */
		public static long getTimeMil(Date date) {
			return date.getTime();
		}
		
		/**
		 *  지금 시간의 PHP 식 10자리의 milliseconds 
		 * @param date
		 * @return
		 */
		public static long getTime() {
			return getTime(new Date());
		}
		
		/**
		 * 입력한 날짜와 String 날짜를 비교해서 입력한 날짜가 같거나 크면 true,  아니면 false
		 * @param date 대상이 되는 날짜
		 * @param sDate 예 : "2016-06-24" 비교 날짜
		 * @return
		 */
		public static boolean isGreaterThanDate(Date date, String sDate){
			
			boolean result = false;
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        Date day2 = null;
	        
	        try {
				day2 = format.parse(sDate);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
	        if(date.compareTo(day2) >= 0){
	        	result = true;
	        }else if(date.compareTo(day2) < 0){
	        	result = false;
	        }
	        
	        return result;
		}

	public static boolean isGreaterThanDate(String date, String sDate){

		boolean result = false;

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date day1 = null;
		Date day2 = null;

		try {
			day1 = format.parse(date);
			day2 = format.parse(sDate);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		if(day1.compareTo(day2) >= 0){
			result = true;
		}else if(day1.compareTo(day2) < 0){
			result = false;
		}
		return result;
	}
		
		/**
		 * 1번 날짜와 2번 날짜와 비교해서 1번 날짜가 같거나 크면 true,  아니면 false
		 * @param date1 대상이 되는 날짜
		 * @param date2  비교 날짜
		 * @return
		 */
		public static boolean isGreaterThanDate(Date date1, Date date2){
			
			boolean result = false;
			
			
			if(date1 == null && date2 == null) {
				return true;
			} else  if(date1 == null &&  date2 != null){
				return false;
			} else if(date1 != null && date2 == null) {
				return true;
			}
			
			date1 = getStringToDate( getDateTime(date1, "yyyy-MM-dd"), "yyyy-MM-dd");
			date2 = getStringToDate( getDateTime(date2, "yyyy-MM-dd"), "yyyy-MM-dd");
				
			
	        if(date1.compareTo(date2) >= 0){
	        	result = true;
	        }else if(date1.compareTo(date2) < 0){
	        	result = false;
	        }
	        
	        return result;
		}
		
		/**
		 * 오늘날짜와 String 날짜와 비교해서 오늘 날짜가  같거나 크면 true,  아니면 false
		 * @param sDate 예 : "2016-06-24" 비교 날짜
		 * @return
		 */
		public static boolean isTodayGreaterThanDate(String sDate){
			
			return isGreaterThanDate(new Date(), sDate);
		}

		/**
		 * 오늘날짜와 String 날짜와 비교해서 오늘 날짜가  같거나 크면 true,  아니면 false
		 * @param sDate 예 : "2016-06-24" 비교 날짜
		 * @return
		 */
		public static boolean isTodayGreaterThanDate(Date sDate){

			return isGreaterThanDate(new Date(), sDate);
		}

		/**
		 * 입력한 String 날짜를 Date 로 변환
		 * @param sDate 예 : "2016-06-24" 비교 날짜
		 * @return
		 */
		public static Date getStringToDate(String date) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return transFormat.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 입력한 String 날짜를 입력한 format 의 Date 로 변환
		 * @param sDate 예 : "2016-06-24" 비교 날짜
		 * @return
		 */
		public static Date getStringToDate(String date, String format) {
			SimpleDateFormat transFormat = new SimpleDateFormat(format);
			try {
				return transFormat.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 입력한 시간(HH:mm)을 오늘날짜 Date.getTime() 으로 반환 - 10자리 long
		 * @param String date : 'HH:mm' 형태만 가능
		 * @return
		 */
		public static long getStringToTime(String date) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			date = today +" "+ date+":00";
			try {
				return transFormat.parse(date).getTime()/1000;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		/**
		 * 입력한 시간(HH:mm)을 오늘날짜 Date.getTime() 으로 반환 - 10자리 long
		 * @param String date : 'HH:mm:ss' 형태만 가능
		 * @return
		 */
		public static long getStringToTime2(String date) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			date = today +" "+ date;
			try {
				return transFormat.parse(date).getTime()/1000;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		/**
		 * Date 를 입력한 포맷으로 다시 변환
		 * @param date
		 * @param format
		 * @return
		 */
		public static Date getDateToFormat(Date date, String format) {
			return getStringToDate( getDateTime(date, format), format);
		}

		/**
		 * Date 에 날짜를 더한값을  yyyy-MM-dd (요일) 형식으로 반환
		 * @param date
		 * @param dateType
		 * @param addNum
		 * @return
		 */
		public static String getAddDateStrDay(Date date, int dateType, int addNum) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(dateType, addNum);
			String today = new SimpleDateFormat("yyyy-MM-dd (E)").format(cal);
			return today;
		}
		
		/**
		 * 입력한 두 날짜간의  해당 요일이 몇번 있는지 검사
		 * @param sdate
		 * @param edate
		 * @param week (1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일)
		 * @return count
		 */
		public static int getDayCount(Date sdate, Date edate, int week) {
			int count = 0;
			int sWeek = 0;
			
			if(sdate == null) {
				sdate = getStringToDate("0000-00-00" , "yyyy-MM-dd");
			}
			
			
			while(isGreaterThanDate(edate, sdate)){
				sWeek = getDayOfWeek(sdate);
				if(sWeek == week) {
					count++;
				}				
				
				sdate = getAddDate(sdate, Calendar.DAY_OF_YEAR, 1);
			}
			
			return count;
		}
		
		
		/**
		 * 두 날짜사의의 관리일수 구하기 (2017-01-01 부터 변경되는 관리 일수)
		 * @param sdate
		 * @param edate
		 * @param prgDuration
		 * @return
		 */
		
		/*
		public static double getPrgDurationDate(Date sdate, Date edate, double prgDuration) {
			
			double prgDurationDate = 0;
			double startDur = 0;
			double endDur = 0;
			
			// 관리종료가 2017-01-01 이후 부터는 토요일을 더해준다.
			if(sdate != null) {
				
				
				if(UtilDate.isGreaterThanDate(edate, "2017-01-01")) {
					
					if(UtilDate.isGreaterThanDate(getStringToDate("2016-12-31", "yyyy-MM-dd"), sdate)) { // 시작일이 2017-01-01 이전일 경우
						
						String strCtdStartDate =  UtilDate.getDateTime(sdate,"yyyyMMdd");
						String strCtdEndDate = UtilDate.getDateTime(edate,"yyyyMMdd");
						
						startDur = UtilDate.subtractDays("20170101", strCtdStartDate);
						endDur = UtilDate.subtractDays(strCtdEndDate, "20170101");
						
						
						if(startDur < 0) {
							startDur = 0;
						}
						
						if(endDur < 0) {
							endDur = 0;
						}
						
						startDur = (long) Math.round(startDur/7);
						endDur = (long) Math.round(endDur/7);
						
						
						
						prgDurationDate += startDur * 6; // 관리기간(주) * 6일   
						prgDurationDate += endDur * 5; // 관리기간(주) * 5일   2017-01-01  일부터 관리일은 5일로 변경이 된다.
					} else {
						
						prgDurationDate += prgDuration * 5; // 관리기간(주) * 5일   2017-01-01  일부터 관리일은 5일로 변경이 된다.
					}
					
				} else {
					prgDurationDate += prgDuration * 6; // 관리기간(주) * 6일
				}
			} else {
				prgDurationDate += prgDuration * 5; // 관리기간(주) * 5일   2017-01-01  일부터 관리일은 5일로 변경이 된다.
			}
			
			// 연기로 인해 관리기간이 늘어 난다면 최대 관리기간은 6일로 계산해서 처리 한다. (연기 계산할때 문제 발생)
			if(prgDurationDate > (prgDuration * 6)) {
				
				// 2017-01-01 년 이후에 관리 시작이라면 5일로 계산
				if(UtilDate.isGreaterThanDate(sdate, "2017-01-01")) {
					prgDurationDate =  prgDuration * 5;
				} else {
					prgDurationDate =  prgDuration * 6;
				}
			}
			
			return prgDurationDate;
		}
		*/
		public static String oneDayAfter(String rDate) throws ParseException {
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdformat.parse(rDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, +1);
			return sdformat.format(cal.getTime());
		}

		/**
		 * 현재 시간,분 과 파라미터 String 값 비교
		 * @param sDate
		 * @return
		 */
		public static boolean compareTime(String sDate) {
			boolean result = false;

			String nowDate = UtilDate.getCurrent("HH:mm");

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date now = null;
			Date day2 = null;
			try {
				day2 = format.parse(sDate);
				now = format.parse(nowDate);

			} catch (ParseException e) {
				e.printStackTrace();
			}

			if(now.compareTo(day2) >= 0){
				result = true;
			}else if(now.compareTo(day2) < 0){
				result = false;
			}
			return result;
		}

		public static int getLastDayOfMonth(String s_year, String s_month, int day) {

			int year = Integer.parseInt(s_year);
			int month = Integer.parseInt(s_month);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cal = Calendar.getInstance();

			cal.set(year, month-1, day); //월은 -1해줘야 해당월로 인식

			System.out.println(dateFormat.format(cal.getTime()));

			return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}

		/**
		 *  다음 날짜 구하기
		 * @param sDate
		 * @return
		 */
		public static String getAfterDate(String sDate) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String afterDate = null;
			try {
				Date date = format.parse(sDate);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, +1);
				afterDate = format.format(calendar.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return afterDate;
		}


		public static void main(String[] a ) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String afterDate = null;
			try {
				Date date = format.parse(getCurrent("yyyy-MM-dd HH:mm"));
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, +1);
				afterDate = format.format(calendar.getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			System.out.println(afterDate);
		}
}
