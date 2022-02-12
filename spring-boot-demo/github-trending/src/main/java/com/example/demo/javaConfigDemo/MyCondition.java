package com.example.demo.javaConfigDemo;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * 实现自定义条件注解
 * 根据配置文件的值是否配置对某个bean进行加载
 *
 * @author KPQ
 * @date 2022/1/21
 */
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取注解MyConditionAnnotation注解上指定value对应的值,如果没有这个值,则不符合条件
        String[] value = (String[]) metadata.getAnnotationAttributes(MyConditionAnnotation.class.getName()).get("value");
        for (String property : value) {
            if (!StringUtils.hasLength(context.getEnvironment().getProperty(property))) {
                return false;
            }
        }
        return true;
    }
}
