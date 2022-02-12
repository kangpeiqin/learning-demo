package com.example.demo.javaConfigDemo;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现接口，可以做到批量加载
 *
 * @author KPQ
 * @date 2022/1/21
 */
public class AnimalImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Tiger.class.getName()};
    }

}
