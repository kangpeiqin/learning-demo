package com.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商户系统订单状态
 *
 * @author kpq
 * @date 2024/8/21 11:18
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    /**
     * 待支付
     */
    UNPAID(0, "UNPAID", "待支付"),
    /**
     * 待配送
     */
    WAIT_DELIVER(1, "WAIT_DELIVER", "待配送"),
    /**
     * 配送中
     */
    DELIVERING(2, "DELIVERING", "配送中"),
    /**
     * 已取消
     */
    CANCEL(3, "CANCEL", "已取消"),

    /**
     * 已完成
     */
    FINISHED(4, "FINISHED", "已完成");

    private Integer status;

    private String code;

    private String desc;

}
