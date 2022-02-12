package com.example.paydemo.controller;

import cn.hutool.extra.servlet.ServletUtil;
import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.enums.TradeTypeEnum;
import com.example.paydemo.service.pay.PayStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * orderNo: 2021122418372938456734
 * @author KPQ
 * @date 2021/12/14
 */
@RestController
public class OrderController {

    @Autowired
    private PayStrategyService payService;

    @PostMapping("pay")
    public PayResultVO pay(@RequestBody TradeOrder order, HttpServletRequest request) throws Exception {
        order.setIpAddr(ServletUtil.getClientIP(request))
                .setOrderNo("2021122418372938456734")
                .setPaymentWay(TradeTypeEnum.NATIVE.getCode())
                .setAmount(new BigDecimal(0.01))
                .setId(1001231L);
        return payService.pay(order);
    }
}
