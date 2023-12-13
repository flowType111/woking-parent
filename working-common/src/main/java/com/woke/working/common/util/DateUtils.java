package com.woke.working.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author yt
 */
public class DateUtils {
    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取当前unix时间戳
     *
     * @return 当前unix时间戳
     */
    public static long getCurrentUnixTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }

    public static String formatDateToStr(Date date) {
        return DateFormatUtils.format(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static String formatDateToStr(Date date, String format) {
        return DateFormatUtils.format(date, format);
    }

    /**
     * 获取 年
     */
    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取 月
     */
    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取 日
     */
    public static Integer getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取 小时
     */
    public static Integer getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取开始时间 天
     */
    public static Date getDayStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取结束时间 天
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取开始时间 周
     */
    public static Date getWeekStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            dayOfWeek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayOfWeek);
        return getDayStart(cal.getTime());
    }

    /**
     * 获取结束时间 周
     */
    public static Date getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getWeekStart(date));
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return getDayEnd(cal.getTime());
    }

    /**
     * 获取开始时间 月
     */
    public static Date getMonthStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(date), getMonth(date) - 1, 1);
        return getDayStart(cal.getTime());
    }

    /**
     * 获取结束时间 月
     */
    public static Date getMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(date), getMonth(date) - 1, 1);
        int day = cal.getActualMaximum(5);
        cal.set(getYear(date), getMonth(date) - 1, day);
        return getDayEnd(cal.getTime());
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * date1 大于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterThan(Date date1, Date date2) {
        if (date1.compareTo(date2) > 0) {
            return true;
        }
        return false;
    }

    /**
     * date1 小于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean lessThan(Date date1, Date date2) {
        if (date1.compareTo(date2) < 0) {
            return true;
        }
        return false;
    }

    /**
     * date1 等于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean equal(Date date1, Date date2) {
        if (date1.compareTo(date2) == 0) {
            return true;
        }
        return false;
    }

    /**
     * date1 大于等于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean greaterThanOrEqual(Date date1, Date date2) {
        if (date1.compareTo(date2) < 0) {
            return false;
        }
        return true;
    }

    /**
     * date1 小于等于 date2
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean lessThanOrEqual(Date date1, Date date2) {
        if (date1.compareTo(date2) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 计算 差 秒
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcSecond(Date date1, Date date2) {
        long a = date1.getTime();
        long b = date2.getTime();
        int second = (int) ((a - b) / 1000);
        return second;
    }

    /**
     * 计算 差 月
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcMonth(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        int month1 = cal1.get(Calendar.MONTH);
        int month2 = cal2.get(Calendar.MONTH);

        int month = (year1 - year2) * 12 + (month1 - month2);
        return month;
    }

    /**
     * 计算 差 周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcWeek(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        int dayOfWeek = cal1.get(Calendar.DAY_OF_WEEK);
        dayOfWeek = dayOfWeek - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        int day = calcDay(date1, date2);
        int week = day / 7;
        int i;
        if (day > 0) {
            i = (day % 7 + dayOfWeek > 7) ? 1 : 0;
        } else {
            i = (dayOfWeek + week % 7 < 1) ? -1 : 0;
        }
        week = week + i;
        return week;
    }

    /**
     * 计算 差 天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int calcDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);

        if (year1 != year2) {
            //同一年
            int timeDistance = 0;
            for (int i = year2; i < year1; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    //闰年
                    timeDistance += 366;
                } else {
                    //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day1 - day2);
        } else {
            //不同年
            return day1 - day2;
        }
    }

    /**
     * 加减 秒
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + second);
        return cal.getTime();
    }

    /**
     * 加减 天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 加减 周
     *
     * @param date
     * @param week
     * @return
     */
    public static Date addWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * 加减 月
     *
     * @param date
     * @param month
     * @return
     */
    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }
}