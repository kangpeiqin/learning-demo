package com.example.distributedLock.idempotent.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.example.distributedLock.idempotent.service.KeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestPart;

import java.lang.reflect.Parameter;


/**
 * 分布式锁生成
 *
 * @author KPQ
 * @date 2021/12/23
 */
public class DefaultKeyGenerator implements KeyGenerator {

    /**
     * 方法签名 + 方法参数值 ==> 使用 md5 进行加密 作为key
     *
     * @param joinPoint
     * @return
     */
    @Override
    public String generate(ProceedingJoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        //获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        sb.append(methodSignature.toString());
        //获取方法参数
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        //获取方法的入参信息
        Object[] args = joinPoint.getArgs();
        int i = 0;
        for (Parameter parameter : parameters) {
            if (!parameter.isAnnotationPresent(RequestPart.class)) {
                sb.append(args[i]);
            }
            i++;
        }
        //使用 md5 进行加密
        String result = sb.toString();
        return StringUtils.hasLength(result) ? SecureUtil.md5(result) : result;
    }

}
