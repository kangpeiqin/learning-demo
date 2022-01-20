package com.example.payDemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author KPQ
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements IEnum {

    /**
     * 未支付
     */
    UNPAID("UNPAID", "未支付"),

    /**
     * 已支付
     */
    PAID("PAID", "已支付"),

    /**
     * 退款中
     */
    REFUNDING("REFUNDING", "退款中"),

    /**
     * 已退款
     */
    REFUND("REFUND", "已退款");


    private String code;

    private String desc;

}
