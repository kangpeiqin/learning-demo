package com.example.record.AutoConfigTest;

import org.springframework.context.annotation.Import;

/**
 * SpringBoot自动配置：@Import：注解支持导入普通 java 类，并将其声明成一个bean。
 * 主要用于将多个分散的 java config 配置类融合成一个更大的 config 类。
 *
 * 1、@Import(AutoConfigurationImportSelector.class)
 *
 * @author KPQ
 * @since 1.0
 */
@Import({Red.class, ColorImportSelector.class, ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
