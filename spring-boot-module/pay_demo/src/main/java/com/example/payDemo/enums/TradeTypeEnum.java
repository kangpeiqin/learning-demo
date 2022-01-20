package com.example.payDemo.enums;

import com.example.payDemo.service.PayService;
import com.example.payDemo.service.pay.impl.WxPay.WxMinWebPayServiceImpl;
import com.example.payDemo.service.pay.impl.WxPay.WxMpPayServiceImpl;
import com.example.payDemo.service.pay.impl.WxPay.WxNativePayServiceImpl;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 微信交易类型枚举
 *
 * @author KPQ
 * @date 2021/12/9
 */
@AllArgsConstructor
@Getter
public enum TradeTypeEnum implements IEnum {

    /**
     * 扫码支付
     */
    NATIVE(WxPayConstants.TradeType.NATIVE, "原生扫码支付", WxNativePayServiceImpl.class),

    /**
     * H5支付
     */
    MWEB(WxPayConstants.TradeType.MWEB, "H5支付", WxMinWebPayServiceImpl.class),

    //    /**
//     * 刷卡支付
//     */
//    MICROPAY("MICROPAY", "刷卡支付"),

    /**
     * 公众号支付/小程序支付.
     */
    JSAPI(WxPayConstants.TradeType.JSAPI, "公众号支付/小程序支付", WxMpPayServiceImpl.class);

    private String code;

    private String desc;

    private Class<? extends PayService> clazz;

    private static Map<String, Object> cache = Maps.newHashMap();

    static {
        for (TradeTypeEnum type : TradeTypeEnum.values()) {
            cache.put(type.getCode(), type.getClazz());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> getPayService(String type) {
        Assert.hasText(type, "支付方式不能为空");
        Object clazz = cache.get(type);
        Assert.notNull(clazz, "不支持的支付方式");
        return (Class<T>) cache.get(type);
    }

    public static void main(String[] args) {
        getPayService("");
    }

}

