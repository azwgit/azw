package com.example.bq.edmp.utils;


/**
 * Created by tl on 2018/5/28.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期常用方法
 *
 * @author
 */
public class DateUtils {

    public static String getDate(String s) {

        Date d = new Date(Long.parseLong(s));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(d);
    }

    public static String getDateMMdd(String s) {

        Date d = new Date(Long.parseLong(s));
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");

        return sdf.format(d);
    }

    public static String getDateMM(String s) {
        if (!s.isEmpty() && !s.equals(null) && s != null) {
            Date d = new Date(Long.parseLong(s));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            return sdf.format(d);
        }
        return "";
    }


    public static Date strToDateLong(String strDate) {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ParsePosition pos = new ParsePosition(0);
//        Date strtodate = formatter.parse(strDate, pos);
//        back strtodate;

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {

        }
        return date;
    }

    /*
   * 将时间转换为时间戳
   */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
}

