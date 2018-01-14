package com.junshan.pub.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 时间工具类
 * Created by chenjunshan on 2016/9/7.
 */

public class TimeUtils {
    public static int Year;
    public static int Month;
    public static int Day;
    private static long ll_time;

    /**
     * 获取系统时间
     *
     * @param format
     * @return
     */
    public static String getTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 传入日期（2018-10-10）返回时间戳（）
     *
     * @param time YmdTimestamp
     * @return
     */
    public static long getTimestamp01(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    /**
     * 传入日期和时间（2018-10-10 10:25）返回时间戳（）
     *
     * @param time
     * @return dataTimestamp
     */
    public static long getTimestamp02(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        try {
            Date date = sdr.parse(time);
            ll_time = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ll_time;
    }

    /**
     * 传入日期和时间（2018-10-10 10:25:55）返回时间戳（）
     *
     * @param time
     * @return
     */
    public static long getTimestamp03(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date = null;
        try {
            date = sdr.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date != null ? date.getTime() : 0;
    }

    /**
     * 传入时间戳返回时间
     *
     * @param time
     * @return
     */
    private static String DateTime03(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String times = sdr.format(new Date(time));
        if (getTime("yyyy/MM/dd").equals(times.substring(0, 10))) {
            return times.substring(times.length() - 5, times.length());
        }
        return times;
    }

    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14  16:09"）
     *
     * @param time 13位
     * @return
     */
    public static String DateTime02(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String times = sdr.format(new Date(time));
        if (getTime("yyyy-MM-dd").equals(times.substring(0, 10))) {
            return times.substring(times.length() - 5, times.length());
        }
        return times;
    }

    /**
     * 剩余天数
     *
     * @param createdAt
     * @return
     */
    public static int residueDay(String createdAt) {
        //    10分钟之内  刚刚     just now
        //    大于10分钟小于60分钟  提示：多少分钟前     30 minutes ago
        //   大于1个小时  就是多少小时前                2 hours ago
        //   大于一天   就是年月日 时分秒               2017/05/06 17:23
        if (TextUtils.isEmpty(createdAt))
            return 0;
        String currentDate = getCurrentDate();
        long createTime = getTimestamp01(createdAt);
        long onTime = getTimestamp01(currentDate);
        long a = createTime - onTime;
        long day = 1000 * 60 * 60 * 24;
        int residueday = (int) (a / day);
        if (residueday < 0) {
            residueday = 0;
        }
        return residueday;
    }

    /**
     * 选择日期时间过去的
     *
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public static String getPastDate(int mYear, int mMonth, int mDay) {
        String dateTime = getDate01(mYear, mMonth, mDay);
        String currentDate = getCurrentDate();
        if (getTimestamp01(dateTime) < getTimestamp01(currentDate)) {
            return "";
        }
        return dateTime;
    }

    /**
     * 时间格式化为0000-00-00
     *
     * @param mYear
     * @param mMonth
     * @param mDay
     * @return
     */
    public static String getDate01(int mYear, int mMonth, int mDay) {
        String dateTime = "";
        String month = (mMonth + 1) + "";
        String day = mDay + "";

        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        dateTime = mYear + "-" + month + "-" + day;
        return dateTime;
    }

    private static SimpleDateFormat format;

    /**
     * 根据日期获取星期几
     *
     * @param pTime
     * @return
     */
    public static String getWeek(String pTime) {
        String Week = "周";
        if (format == null)
            format = new SimpleDateFormat("yyyy-MM-dd");//也可将此值当参数传进来
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                Week = "日";
                break;
            case 2:
                Week = "一";
                break;
            case 3:
                Week = "二";
                break;
            case 4:
                Week = "三";
                break;
            case 5:
                Week = "四";
                break;
            case 6:
                Week = "五";
                break;
            case 7:
                Week = "六";
                break;
            default:
                break;
        }
        return Week;
    }

    /**
     * 获取当前月第一个星期的天数
     *
     * @param pTime
     * @return
     */
    public static int getNum(String pTime) {
        int num = 0;
        if (format == null)
            format = new SimpleDateFormat("yyyy-MM-dd");//也可将此值当参数传进来
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch (c.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                num = 0;
                break;
            case 2:
                num = 1;
                break;
            case 3:
                num = 2;
                break;
            case 4:
                num = 3;
                break;
            case 5:
                num = 4;
                break;
            case 6:
                num = 5;
                break;
            case 7:
                num = 6;
                break;
            default:
                break;
        }
        return num;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

}
