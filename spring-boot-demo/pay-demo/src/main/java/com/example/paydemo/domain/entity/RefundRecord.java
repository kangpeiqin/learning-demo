package com.example.paydemo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author KPQ
 * @date 2021/12/9
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "refund_record")
public class RefundRecord extends BaseEntity {

    /**
     * 系统订单号
     */
    private String orderNo;

    /**
     * 退款订单号
     */
    private String refundNo;


    /**
     * 支付方式
     */
    private String paymentWay;


    /**
     * 退款金额
     */
    private BigDecimal refundAmount;


    /**
     * 订单流水号
     */
    private String refundTransactionNo;


    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

}
