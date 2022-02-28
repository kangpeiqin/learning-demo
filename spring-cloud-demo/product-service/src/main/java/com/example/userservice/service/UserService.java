package com.example.userservice.service;

import com.example.userservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 使用 Feign 调用 User 服务，调用失败则采用服务降级措施
 *
 * @author KPQ
 * @since 1.0
 */
@FeignClient(value = "USERSERVICE", fallback = UserServiceFallback.class)
public interface UserService {

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    List<User> findAll();

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User load(@PathVariable("id") Long id);


}
