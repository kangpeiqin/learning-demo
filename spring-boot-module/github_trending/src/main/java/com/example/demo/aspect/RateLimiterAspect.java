package com.example.demo.aspect;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用 Guava 提供的 @link{RateLimiter}进行限流
 * 参考文章链接：https://my.oschina.net/hanchao/blog/1833612
 * https://juejin.cn/post/6844903687026901000
 *
 * @author KPQ
 * @date 2021/11/22
 */
@Aspect
@Component
@Slf4j
public class RateLimiterAspect {

    private static final ConcurrentHashMap<String, RateLimiter> LIMIT_CACHE = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.example.demo.aspect.Limiter)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limiter limiter = AnnotationUtils.findAnnotation(method, Limiter.class);
        if (limiter != null && limiter.qps() > Limiter.NOT_LIMIT) {
            double qps = limiter.qps();
            if (LIMIT_CACHE.get(method.getName()) == null) {
                LIMIT_CACHE.put(method.getName(), RateLimiter.create(qps));
            }
            log.debug("【{}】的QPS设置为: {}", method.getName(), LIMIT_CACHE.get(method.getName()).getRate());
            // 尝试获取令牌
            if (LIMIT_CACHE.get(method.getName()) != null &&
                    !LIMIT_CACHE.get(method.getName()).tryAcquire(limiter.timeout(), limiter.timeUnit())) {
                throw new RuntimeException("访问太频繁了，请稍后再试...");
            }
        }
        return point.proceed();
    }
}