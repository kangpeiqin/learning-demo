package com.example.distributedLock.idempotent.service;

import com.example.distributedLock.idempotent.annotation.Idempotent;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 幂等性处理
 *
 * @author KPQ
 * @date 2021/12/23
 */
public interface IdempotentInterceptor {

    /**
     * 拦截需要执行的方法，判断是否有重复提交的情况
     *
     * @param joinPoint
     * @param idempotent
     * @return
     * @throws Exception
     */
    Object determine(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Exception;

}
