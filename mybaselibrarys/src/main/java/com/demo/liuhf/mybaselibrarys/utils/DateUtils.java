package com.demo.liuhf.mybaselibrarys.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtils {

    private static DateUtils self;
    private Map<String, String> wordForMonth;

    //  农历月份
    private static String[] lunarMonth = {"正月", "二月", "三月", "四月", "五月",
            "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    //  农历日
    private static String[] lunarDay = {"初一", "初二", "初三", "初四", "初五",
            "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四",
            "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三",
            "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十"};

    public DateUtils() {
        self = this;
        wordForMonth = new HashMap<String, String>() {
            {
                put("refresh_01", "一月");
                put("refresh_02", "二月");
                put("refresh_03", "三月");
                put("04", "四月");
                put("05", "五月");
                put("06", "六月");
                put("07", "七月");
                put("08", "八月");
                put("09", "九月");
                put("10", "十月");
                put("11", "十一月");
                put("12", "十二月");
            }
        };
    }


    public static DateUtils getSelf() {
        if (null == self) {
            self = new DateUtils();
        }
        return self;
    }

    public String getStr(int n) {
        if (n >= 0 && n < 10) {
            return "0" + n;
        } else if (n < 0 && n > -10) {
            return "-0" + (-n);
        } else {
            return n + "";
        }
    }

    public String getWeekStr(String tag, int DAY_OF_WEEK) {
        String str = "日";
        switch (DAY_OF_WEEK) {
            case Calendar.MONDAY:
                str = "一";
                break;
            case Calendar.TUESDAY:
                str = "二";
                break;
            case Calendar.WEDNESDAY:
                str = "三";
                break;
            case Calendar.THURSDAY:
                str = "四";
                break;
            case Calendar.FRIDAY:
                str = "五";
                break;
            case Calendar.SATURDAY:
                str = "六";
                break;
        }
        return tag + str;
    }

    public Date getTimeDate(long time) {
        return new Date(time > 0 ? time : System.currentTimeMillis());
    }

    public Date getTimeDate(String timeStr) {
        long time = System.currentTimeMillis();
        if (StringUtils.isNotEmpty(timeStr)) {
            try {
                time = Long.parseLong(timeStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Date(time);
    }

    //yyyy-MM-dd HH:mm:ss
    public String getTimeStr(long time, String formatterType) {
        return getTimeStr(new Date(time), formatterType);
    }

    //yyyy-MM-dd HH:mm:ss
    public String getTimeStr(String timeStr, String formatterType) {
        return getTimeStr(getTimeDate(timeStr), formatterType);
    }

    //yyyy-MM-dd HH:mm:ss
    public String getTimeStr(Date date, String formatterType) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatterType);
        if (null != date) {
            return formatter.format(date);
        } else {
            return formatter.format(new Date(System.currentTimeMillis()));
        }
    }

    public long getTime(String time, String formatterType) {
        Date date = getDate(time, formatterType);
        return null != date ? date.getTime() : System.currentTimeMillis();
    }

    public Date getDate(String time, String formatterType) {
        Date l = new Date(System.currentTimeMillis());
        if (StringUtils.isNotEmpty(time)) {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formatterType);
                l = formatter.parse(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return l;
    }

    public String getChatTime(long publishTime) {
        return getChatTime(new Date(publishTime));
    }

    public String getChatTime(Date publish) {
        try {
            long secondDiff = (System.currentTimeMillis() - publish.getTime()) / 1000;
            if (secondDiff >= 86400) {// 天
                SimpleDateFormat DF = new SimpleDateFormat("MM-dd HH:mm");
                return DF.format(publish);
            } else {
                if (secondDiff > 3600) {// 小时
                    return (int) (secondDiff / 3600) + "小时前";
                } else {
                    secondDiff = (int) (secondDiff / 60);// 分钟
                    if (secondDiff < 1) {
                        secondDiff = 1;
                    }
                    return secondDiff + "分钟前";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getSignTime(long time) {
        try {
            Date publish = new Date(time);
            long secondDiff = (System.currentTimeMillis() - publish.getTime()) / 1000;
            if (secondDiff >= 86400) {// 天
                return (int) (secondDiff / 86400) + "天前";
            } else {
                if (secondDiff > 3600) {// 小时
                    return (int) (secondDiff / 3600) + "小时前";
                } else {
                    secondDiff = (int) (secondDiff / 60);// 分钟
                    if (secondDiff < 1) {
                        secondDiff = 1;
                    }
                    return secondDiff + "分钟前";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    public String getChatListTime(long publishTime) {
        Date publish;
        try {
            if (publishTime <= 0) {
                publishTime = System.currentTimeMillis();
            }
            publish = new Date(publishTime);
            long secondDiff = (System.currentTimeMillis() - publish.getTime()) / 1000;
            SimpleDateFormat DF = new SimpleDateFormat(secondDiff >= 86400 ? "MM-dd HH:mm" : "HH:mm");
            return DF.format(publish);
        } catch (Exception e) {
            return "";
        }
    }

    public String videoForTime(int timeMs) {
        if (timeMs <= 0 || timeMs >= 24 * 60 * 60 * 1000) {
            return "00:00";
        }
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        StringBuilder mFormatBuilder = new StringBuilder();
        Formatter mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
        if (hours > 0) {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        } else {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }


    /**
     * 获取农历月份
     *
     * @return
     */
    public static String getLunarMonth() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarMonth[lunarDate[1] - 1];
    }

    /**
     * 获取农历日
     *
     * @return
     */
    public static String getLunarDay() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int[] lunarDate = LunarCalendar.solarToLunar(year, month, day);
        return lunarDay[lunarDate[2] - 1];
    }

    /**
     * @param date
     * @return 当前日期是星期几
     */

    public static String getWeekOfDate(String date) throws ParseException {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        cal.setTime(format.parse(date));

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (w < 0)
            w = 0;
        return weekDays[w];

    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
          String time= String.valueOf(sdf.parse(date_str).getTime() / 1000);
            return time;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
