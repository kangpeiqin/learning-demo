package com.example.distributedLock.idempotent.service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 分布式锁 key 生成器
 *
 * @author KPQ
 * @date 2021/12/23
 */
public interface KeyGenerator {

    /**
     * 生成key
     *
     * @param joinPoint
     * @return
     */
    String generate(ProceedingJoinPoint joinPoint);

}
