package com.example.demo.javaConfigDemo;

import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 过滤一些bean，让其不注册到 IOC 容器当中
 *
 * @author KPQ
 * @date 2022/1/21
 */
@Component
public class MyTypeExcludeFilter extends TypeExcludeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        return User.class.getName().equals(metadataReader.getClassMetadata().getClassName());
    }

}
