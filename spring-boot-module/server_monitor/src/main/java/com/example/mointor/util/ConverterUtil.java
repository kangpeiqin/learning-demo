package com.example.mointor.util;

import java.util.Arrays;

/**
 * @author KPQ
 * @date 2021/12/7
 */
public class ConverterUtil {
    private ConverterUtil() {
    }

    public static String getFrequency(long[] hertzArray) {
        long totalFrequency = Arrays.stream(hertzArray).sum();
        long hertz = totalFrequency / hertzArray.length;

        if ((hertz / 1E+6) > 999) {
            return (Math.round((hertz / 1E+9) * 10.0) / 10.0) + " GHz";
        } else {
            return Math.round(hertz / 1E+6) + " MHz";
        }
    }

    public static String getConvertedCapacity(long bits) {
        if ((bits / 1.049E+6) > 999) {
            if ((bits / 1.074E+9) > 999) {
                return (Math.round((bits / 1.1E+12) * 10.0) / 10.0) + " TiB";
            } else {
                return Math.round(bits / 1.074E+9) + " GiB";
            }
        } else {
            return Math.round(bits / 1.049E+6) + " MiB";
        }
    }
}
