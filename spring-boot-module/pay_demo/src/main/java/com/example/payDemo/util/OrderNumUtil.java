package com.example.payDemo.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 商户订单号生成：订单日期+8位随机数
 *
 * @author KPQ
 * @date 2021/12/9
 */
public class OrderNumUtil {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String genOderNum() {
        return formatter.format(LocalDateTime.now()) + RandomStringUtils.randomNumeric(8);
    }

    public static void main(String[] args) {
        System.out.println(genOderNum());
    }
}
