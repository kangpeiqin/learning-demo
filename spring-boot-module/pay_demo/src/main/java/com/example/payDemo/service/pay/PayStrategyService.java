package com.example.payDemo.service.pay;

import com.example.payDemo.domain.entity.TradeOrder;
import com.example.payDemo.domain.vo.PayResultVO;
import com.example.payDemo.enums.TradeTypeEnum;
import com.example.payDemo.service.PayService;
import com.example.payDemo.util.SpringUtil;
import org.springframework.stereotype.Service;

/**
 * @author KPQ
 * @date 2021/12/16
 */
@Service
public class PayStrategyService {

    public PayResultVO pay(TradeOrder order) throws Exception {
        PayService<PayResultVO, TradeOrder> payService = SpringUtil.getBean(TradeTypeEnum.getPayService(order.getPaymentWay()));
        return payService.pay(order);
    }

}
