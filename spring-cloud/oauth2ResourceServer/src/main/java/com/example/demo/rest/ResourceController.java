package com.example.demo.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author KPQ
 * @date 2021/11/19
 */
@RestController
public class ResourceController {

    @PreAuthorize("#oauth2.hasScope('READ')")
    @GetMapping("/read")
    public String read() {
        return "READ";
    }

    /**
     * scope 有 WRITE 的用户资源才能访问
     *
     * @return WRITE
     */
    @PreAuthorize("#oauth2.hasScope('WRITE')")
    @GetMapping("/write")
    public String write() {
        return "WRITE";
    }


    @PreAuthorize("#oauth2.hasScope('READ')")
    @GetMapping("/userInfo")
    public User userInfo() {
        return new User("user", "password", Collections.emptyList());
    }

}
