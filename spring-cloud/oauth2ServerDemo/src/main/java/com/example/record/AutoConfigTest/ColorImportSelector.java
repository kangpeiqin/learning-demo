package com.example.record.AutoConfigTest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 实现 ImportSelector 将组件注册到 IOC 容器中
 *
 * @author KPQ
 * @since 1.0
 */
public class ColorImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{Blue.class.getName(), Green.class.getName()};
    }

}
