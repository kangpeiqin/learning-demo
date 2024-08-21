package com.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付状态
 *
 * @author xxx
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

    /**
     * 未支付
     */
    UNPAID(0, "UNPAID", "未支付"),

    /**
     * 已支付
     */
    PAID(1, "PAID", "已支付");

    private Integer status;

    private String code;

    private String desc;

}
