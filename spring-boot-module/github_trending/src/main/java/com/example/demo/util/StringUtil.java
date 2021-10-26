package com.example.demo.util;

/**
 * @author KPQ
 * @date 2021/10/26
 */
public class StringUtil {

    private StringUtil(){}

    public static String get(Object o) {
        if (o == null) {
            return null;
        }

        return o.toString();
    }

}
