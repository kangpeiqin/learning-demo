package com.example.paydemo.service.pay;

import com.example.paydemo.domain.entity.TradeOrder;
import com.example.paydemo.domain.vo.PayResultVO;
import com.example.paydemo.enums.TradeTypeEnum;
import com.example.paydemo.service.PayService;
import com.example.paydemo.util.SpringUtil;
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
