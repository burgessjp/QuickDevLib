package me.solidev.library.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by _SOLID
 * Date:2016/5/10
 * Time:9:58
 */
public class DateUtil {
    public static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


    public static String formatDate(Date date) {
        return formatDate.format(date);
    }

    public static String formatDate(String date) {
        return formatDate(parseDate(date));
    }


    public static String formatDateTime(Date date) {
        return formatDateTime.format(date);
    }


    public static String parseDate(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        Date date = calendar.getTime();
        return formatDate(date);
    }

    public static String parseDateTime(long timeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        Date date = calendar.getTime();
        return formatDateTime(date);
    }

    public static Date parseDate(String date) {
        Date mDate = null;
        try {
            mDate = formatDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mDate;
    }

    public static Date parseDateTime(String date) {
        Date mDate = null;
        try {
            mDate = formatDateTime.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return mDate;
    }


    public static String friendlyTime(Date date) {
        return friendlyTime(formatDateTime(date));
    }

    public static String friendlyTime(String date) {
        Date time = parseDateTime(date);
        if (time == null) {
            return "Unknown";
        }
        String fTime = "";
        Calendar cal = Calendar.getInstance();

        // 判断是否是同一天
        String curDate = formatDate.format(cal.getTime());
        String paramDate = formatDate.format(time);
        if (curDate.equals(paramDate)) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                fTime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                fTime = hour + "小时前";
            return fTime;
        }

        long lt = time.getTime() / 86400000;
        long ct = cal.getTimeInMillis() / 86400000;
        int days = (int) (ct - lt);
        if (days == 0) {
            int hour = (int) ((cal.getTimeInMillis() - time.getTime()) / 3600000);
            if (hour == 0)
                fTime = Math.max(
                        (cal.getTimeInMillis() - time.getTime()) / 60000, 1)
                        + "分钟前";
            else
                fTime = hour + "小时前";
        } else if (days == 1) {
            fTime = "昨天";
        } else if (days == 2) {
            fTime = "前天";
        } else if (days > 2 && days <= 10) {
            fTime = days + "天前";
        } else if (days > 10) {
            fTime = formatDate.format(time);
        }
        return fTime;
    }
}
