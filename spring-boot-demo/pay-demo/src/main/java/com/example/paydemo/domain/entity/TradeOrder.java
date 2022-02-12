package com.example.paydemo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * 订单表
 *
 * @author KPQ
 * @date 2021/12/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName(value = "trade_order")
public class TradeOrder extends BaseEntity {

    /**
     * 商户订单号，由我们系统自己生成
     */
    private String orderNo;

    /**
     * 订单的金额
     */
    private BigDecimal amount;

    /**
     * 实际付款的金额
     */
    private BigDecimal actualAmount;

    /**
     * 支付方式
     */
    private String paymentWay;

    /**
     * 支付状态
     */
    private String payStatus;


    /**
     * 支付用户id
     */
    private String userId;

    /**
     * 用户小程序openId
     */
    private String openId;

    /**
     * 交易订单号
     */
    private String tractionNo;

    /**
     * ip地址
     */
    private String ipAddr;

    /**
     * 订单描述
     */
    private String description;

}
