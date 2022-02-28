package com.example.userservice.rest;

import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author KPQ
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> list() {
        return this.userRepository.findAll();
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public User detail(@PathVariable("id") Long id) {
        User user = this.userRepository.getOne(id);
        return user;
    }

}
