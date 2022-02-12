package com.example.record.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kpq
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * 创建官网后台接口文档
     */
    @Bean
    public Docket webApi() {
        ParameterBuilder header = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        header.name("Authorization").description("登陆token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).defaultValue("Bearer ");
        pars.add(header.build());
        //使用链式调用
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .globalOperationParameters(pars)
                .groupName("webApi")
                .select()
                //扫描基础包
                .apis(RequestHandlerSelectors.basePackage("com.example.record.rest.web"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    /**
     * api信息
     */
    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("官网后台接口")
                .description("官网后台接口")
                .termsOfServiceUrl("https://www.kangpeiqin.cn/#/index")
                .version("1.0")
                .build();
    }

    /**
     * 移动端接口文档
     */
    @Bean("mb")
    public Docket mobile() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(mobileInfo())
                .groupName("mobile")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.record.rest.mobile"))
                .paths(PathSelectors.ant("/mb/**"))
                .build();
    }

    private ApiInfo mobileInfo() {
        return new ApiInfoBuilder()
                .title("移动端接口")
                .description("移动端接口")
                .termsOfServiceUrl("https://www.kangpeiqin.cn/#/index")
                .version("1.0")
                .build();
    }

}
