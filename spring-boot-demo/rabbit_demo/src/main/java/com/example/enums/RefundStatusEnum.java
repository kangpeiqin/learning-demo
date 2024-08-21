package com.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 退款状态
 *
 * @author xxxx
 * @date 2024/8/21 11:18
 */
@Getter
@AllArgsConstructor
public enum RefundStatusEnum {
    /**
     * 未退款
     */
    UN_REFUND(0, "UN_REFUND", "未退款"),

    REFUNDING(1, "REFUNDING", "退款中"),

    REFUND(2, "REFUND", "已退款");

    private Integer status;

    private String code;

    private String desc;


}
