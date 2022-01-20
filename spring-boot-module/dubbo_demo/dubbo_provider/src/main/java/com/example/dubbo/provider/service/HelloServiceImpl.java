package com.example.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbo.common.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * Hello服务实现
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-25 16:58
 */
@Service
@Component
@Slf4j
public class HelloServiceImpl implements HelloService {

    public String sayHello(String name) {
        log.info("================================");
        return "say hello to: " + name;
    }

}
