package com.example.distributedlock.idempotent.annotation;

import com.example.distributedlock.idempotent.service.KeyGenerator;
import com.example.distributedlock.idempotent.service.impl.DefaultKeyGenerator;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 幂等性注解
 *
 * @author KPQ
 * @date 2021/12/23
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {

    /**
     * 手动指定分布式锁的key
     */
    String key() default "";

    /**
     * 动态生成分布式锁
     */
    Class<? extends KeyGenerator> generator() default DefaultKeyGenerator.class;

    /**
     * 锁定的时间，默认不指定任何时间
     */
    long lockTime() default -1;

    /**
     * 锁定时间的单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * 如果发生重复提交, 是否允许抛出异常
     *
     * @return true-抛出异常; false-不做处理
     */
    boolean throwIfRepeat() default true;

    /**
     * 如果中间件宕机, 是否允许继续调用
     *
     * @return true-允许继续调用; false-不允许调用, 会抛出异常
     */
    boolean allowIfDown() default true;

}
