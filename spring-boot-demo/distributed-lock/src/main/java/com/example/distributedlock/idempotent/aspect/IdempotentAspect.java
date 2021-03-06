package com.example.distributedlock.idempotent.aspect;

import com.example.distributedlock.idempotent.annotation.Idempotent;
import com.example.distributedlock.idempotent.service.IdempotentInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author KPQ
 * @date 2021/12/23
 */
@Aspect
@Component
public class IdempotentAspect implements Ordered {

    private final IdempotentInterceptor idempotentInterceptor;

    @Autowired
    public IdempotentAspect(IdempotentInterceptor idempotentInterceptor) {
        this.idempotentInterceptor = idempotentInterceptor;
    }

    /**
     * 环绕通知, 对所有标注了{@link Idempotent}注解的接口, 会进行幂等性判断.
     * 如果允许执行则直接调用原方法, 反之抛出{@link RepeatedSubmitException}异常.
     * 具体看接口{@link IdempotentInterceptor}的实现类
     *
     * @param joinPoint   切入点
     * @param idempotent 注解配置
     * @return Object
     */
    @Around("@annotation(idempotent)")
    public Object handler(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Exception{
        return idempotentInterceptor.determine(joinPoint, idempotent);
    }

    /**
     * 调整在某些业务场景下, 切面的优先级
     */
    @Override
    public int getOrder() {
        return -1;
    }
}

