package com.zzti.fengyongge.timepicker.timeview;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class TimeUtil {

    /**
     * 处理时间
     *
     * @param
     * @return
     */
    public static String handTime(String time) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (StringUtils.isEmpty(time)) {
            return "";
        }
        try {

            Date date = format.parse(time);
            long tm = System.currentTimeMillis();// 当前时间戳
            long tm2 = date.getTime();// 发表动态的时间戳
            long d = (tm - tm2) / 1000;// 时间差距 单位秒
            if ((d / (60 * 60 * 24)) > 1) {
                return time.substring(0, 10);
            } else if ((d / (60 * 60 * 24)) > 0) {
                return "昨天";
            } else if ((d / (60 * 60)) > 0) {
                return d / (60 * 60) + "小时前";
            } else if ((d / 60) > 0) {
                return d / 60 + "分钟前";
            } else {
                // return d + "秒前";
                return "刚刚";
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    public static String getTime(long time) {
        String sb;
        Date dat = new Date(time);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb = format.format(gc.getTime());
        return sb;
    }

    public static String getDay() {
        String s;
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        s = timeformat.format(date);

        return s;
    }

    public static String getYestoday() {
        String yesterday;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        yesterday = new SimpleDateFormat("yyyy-MM-dd ").format(cal.getTime());

        return yesterday;
    }


    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }

    public static String getCurrentTime() {
        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
    }


    public static String getNowWeekMonday(Date date) {//获取本周一
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()) + "";
    }


    public static Date getLastWeekSunday() {//上周周天
        Calendar date = Calendar.getInstance(Locale.CHINA);
        date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天
        date.add(Calendar.WEEK_OF_MONTH, -1);//周数减一，即上周
        date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天
        return date.getTime();

    }

    public static String getMondayOfThisWeek() {
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return timeformat.format(c.getTime());
    }



    /**
     * 根据日期字符串判断当月第几周
     * @param str
     * @return
     * @throws Exception
     */
    public static int getWeek(String str) throws Exception {
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date =sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //第几周
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        //第几天，从周日开始
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return week;
    }



}