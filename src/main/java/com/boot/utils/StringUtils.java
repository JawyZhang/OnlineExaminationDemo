package com.boot.utils;

/**
 * @Author Mango
 * @Date 2020-05-30 16:18
 */
public class StringUtils {
    public static String getSubLengthStr(String str, int length) {
        System.out.println(str);
        if (str.length() >= 6) {
            System.out.println(str.substring(str.length() - length));
            return str.substring(str.length() - length);
        } else {
            int count = length - str.length();
            for (int i = 0; i < count; i++) {
                str = "0" + str;
            }
            System.out.println(str);
            return str;
        }
    }
}
