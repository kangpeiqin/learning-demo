package com.example.userservice.service;

import com.example.userservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务降级
 *
 * @author KPQ
 * @date 2021/10/14
 */
@Component
@Slf4j
public class UserServiceFallback implements UserService {
    @Override
    public List<User> findAll() {
        log.info("fall back...");
        return null;
    }

    @Override
    public User load(Long id) {
        log.info("fall back...");
        return null;
    }
}
