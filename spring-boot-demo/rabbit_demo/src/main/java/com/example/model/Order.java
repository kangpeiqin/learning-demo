package com.example.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zzz
 * @date 2024/8/20 17:13
 */
@Data
@Accessors(chain = true)
public class Order implements Serializable {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单名称
     */
    private String name;

    /**
     * 微信系统订单状态 --- 支付状态
     */
    private Integer payStatus;

    /**
     * 商户系统内部订单状态（未支付、已发货）
     */
    private Integer orderStatus;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 订单金额
     */
    private BigDecimal amount;
}
