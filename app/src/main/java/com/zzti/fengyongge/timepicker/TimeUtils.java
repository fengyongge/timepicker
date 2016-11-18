package com.zzti.fengyongge.timepicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fengyongge on 2016/11/18 0018.
 */

public class TimeUtils {

    /**
     * 时间转换格式-年月日
     * @param time
     * @return
     */
    public static String convertTime(String time){
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date1);
        return  dateString;
    }

    /**
     * 时间转换格式-年月
     * @param time
     * @return
     */
    public static String convertTime1(String time){
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = format1.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(date1);
        return  dateString;
    }
}
