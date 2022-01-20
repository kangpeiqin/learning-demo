package com.example.payDemo.domain.vo;

import com.example.payDemo.domain.entity.TradeOrder;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author KPQ
 * @date 2021/12/14
 */
@Data
@AllArgsConstructor
public class PayResultVO {
    /**
     * 支付返回信息
     */
    private Object payRes;


    /**
     * 订单信息
     */
    private TradeOrder order;

}
