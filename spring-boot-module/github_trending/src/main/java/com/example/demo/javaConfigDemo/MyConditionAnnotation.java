package com.example.demo.javaConfigDemo;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author kangpeiqin
 * @date 2022/1/21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(MyCondition.class)
public @interface MyConditionAnnotation {
    String[] value() default {};
}
