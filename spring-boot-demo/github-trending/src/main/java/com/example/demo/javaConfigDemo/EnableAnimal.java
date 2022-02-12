package com.example.demo.javaConfigDemo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author kangpeiqin
 * @date 2022/1/21
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({Dog.class, AnimalRegisterConfiguration.class, AnimalImportSelector.class, AnimalImportBeanDefinitionRegistrar.class})
public @interface EnableAnimal {
}
