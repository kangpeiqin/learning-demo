package com.example.payDemo.service;

/**
 * 流程：
 * 用户下单 ---> 创建订单 ----> 调用统一下单 API --- 微信生成预支付交易
 * ----------------------------------------------
 * 如何防止创建重复订单？
 * 幂等性问题考虑: 保证多次调用接口的结果保持一致。
 *
 * @author kangpeiqin
 * @date 2021/12/9
 */
public interface PayService<R, T> {

    /**
     * - 商户系统订单创建
     *
     * 创建订单，确保幂等性，防止重复创建订单
     *
     * @param param
     * @return 创建成功返回结果
     * @throws Exception 创建失败抛出异常
     */
    R createOrder(T param) throws Exception;

    /**
     * - 第三方支付系统订单创建
     * 订单预支付(创建预支付订单)
     * 商户系统  请求  第三方支付系统订单创建接口
     *
     * @param param
     * @return 预支付
     * @throws Exception
     */
    R pay(T param) throws Exception;


    /**
     * 退款
     *
     * @param param
     * @return
     * @throws Exception
     */
    boolean refund(T param) throws Exception;

    /**
     * 支付订单日志保存
     *
     * @param param
     * @return
     */
    void savePayLog(T param);


}
