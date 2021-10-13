package com.example.userService.rest;

import com.example.userService.entity.User;
import com.example.userService.repository.UserRepository;
import com.example.userService.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author KPQ
 * @date 2021/10/13
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public Result<List<User>> list() {
        return Result.success(this.userRepository.findAll());
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<User> detail(@PathVariable("id") Long id) {
        User user = this.userRepository.getOne(id);
        return Result.success(user);
    }

}
