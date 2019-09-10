package com.aoke.apartmentsystem.common.utils;

public class StringUtil {

    /**
     * 补齐不足长度
     * @param length 长度
     * @param number 数字
     * @return
     */
    public static String lpad(int length, int number) {
        String f = "%0" + length + "d";
        return String.format(f, number);
    }
}
