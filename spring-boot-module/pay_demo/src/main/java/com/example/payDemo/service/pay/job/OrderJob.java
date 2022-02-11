package com.example.payDemo.service.pay.job;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author KPQ
 * @date 2022/2/11
 */
@Slf4j
@Component
public class OrderJob {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 每30秒同步待支付订单状态,主动向第三方系统进行订单状态同步
     **/
    @Scheduled(cron = "0/30 * * * * ? ")
    public void orderPayStatus() {
        //查询订单状态为未支付的订单
        try {
            long t1 = System.currentTimeMillis();
            //支付类型判断 订单查询
            //判断订单类型,微信订单查询,根据商户订单号进行查询,根据商户订单号进行查询
            WxPayOrderQueryResult wxPayOrderQueryResult = this.wxPayService.queryOrder(null, "");

            //支付宝订单查询,根据商户订单号进行查询
            AlipayTradeQueryResponse result = Factory.Payment.Common().query("");

            long t2 = System.currentTimeMillis();
            log.info("处理时长:{}ms", (t2 - t1));
        } catch (Exception e) {
            log.error("支付状态同步异常:{}", e.getMessage(), e);
        }

    }

}
