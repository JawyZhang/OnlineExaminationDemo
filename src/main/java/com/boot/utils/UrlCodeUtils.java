package com.boot.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Author Mango
 * @Date 2020-04-09 10:32
 */
public class UrlCodeUtils {
    public static String getUrlString(String str) {
        String urlStr = "";
        try {
            urlStr = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlStr;
    }
}
