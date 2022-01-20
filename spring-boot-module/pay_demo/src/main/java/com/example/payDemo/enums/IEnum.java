package com.example.payDemo.enums;

/**
 * @author kangpeiqin
 * @date 2021/12/9
 */
public interface IEnum<T> {

    /**
     * 获取code
     * @return
     */
    T getCode();

    /**
     * 获取详情
     * @return
     */
    T getDesc();

}
