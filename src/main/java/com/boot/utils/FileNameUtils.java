package com.boot.utils;

/**
 * @Author Mango
 * @Date 2020-05-14 13:11
 */
public class FileNameUtils {
    public static String getExtName(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }
}
