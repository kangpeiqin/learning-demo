package com.example.demo.aspect;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author kangpeiqin
 * @date 2021/11/22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limiter {

    int NOT_LIMIT = 0;

    /**
     * qps：Queries-per-second， 每秒查询率，QPS = req/sec = 请求数/秒
     */
    @AliasFor("qps") double value() default NOT_LIMIT;

    @AliasFor("value") double qps() default NOT_LIMIT;

    /**
     * 超时时长
     */
    int timeout() default 0;

    /**
     * 超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

}
