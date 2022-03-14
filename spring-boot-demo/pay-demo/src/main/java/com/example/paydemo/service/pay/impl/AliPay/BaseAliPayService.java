package com.example.paydemo.service.pay.impl.AliPay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.util.OrderNumUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author KPQ
 * @date 2021/12/16
 */
@Slf4j
public abstract class BaseAliPayService {

    public void savePayLog(TradeOrder param) {

    }

    public PayResultVO createOrder(TradeOrder param) throws Exception {
        String orderNo = param.getOrderNo();
        //TODO 根据 orderNo 查询数据库，判断是否产生预支付订单
        if (orderNo == null) {
            //生成预支付订单号
            param.setOrderNo(OrderNumUtil.genOderNum());
        }
        return new PayResultVO(null, param);
    }

    /**
     * 支付宝退款
     *
     * @param param
     * @return
     * @throws Exception
     */
    public boolean refund(TradeOrder param) throws Exception {
        AlipayTradeRefundResponse refundResponse = Factory.Payment.Common().refund(param.getOrderNo(), param.getAmount().toString());
        return false;
    }


}

class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * char c = s.charAt(i);
     * if (c >= '0' && c <= '9') {
     * for (int j = i + 1; j < s.length(); j++) {
     * char cj = s.charAt(j);
     * if (cj < '0' || cj > '9') {
     * break;
     * } else {
     * <p>
     * }
     * }
     * list.add((int) c);
     * }
     *
     * @param s string字符串
     * @return int整型ArrayList
     */
    public static ArrayList<Integer> extraNum(String s) {
        ArrayList<Integer> list = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return list;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else {
                sb.append(",");
            }
        }
        String[] arr = sb.toString().split(",");

        for (String str : arr) {
            if (!"".equals(str)) {
                list.add(Integer.valueOf(str));
            }
        }
        return list;
    }

//    public int validNum(int N) {
//
//
//        // write code here
//    }
//
//    private String getAll(int n) {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//
//        }
//    }

    public static void main(String[] args) {
        System.out.println(extraNum("c012u14i8"));
    }
}