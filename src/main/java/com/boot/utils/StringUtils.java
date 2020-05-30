package com.boot.utils;

/**
 * @Author Mango
 * @Date 2020-05-30 16:18
 */
public class StringUtils {
    public static String getSubLengthStr(String str, int length) {
        if (str.length() >= 6) {
            return str.substring(str.length() - length);
        } else {
            int count = length - str.length();
            for (int i = 0; i < count; i++) {
                str = "0" + str;
            }
            return str;
        }
    }
}
