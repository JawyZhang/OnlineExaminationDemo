package com.boot.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Mango
 * @Date 2020-06-02 20:13
 */
public class TimeUtils {
    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static long getMillisTime(String time) {
        try {
            return df.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }
}
