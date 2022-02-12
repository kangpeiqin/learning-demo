package com.example.demo.rest;

import com.example.demo.domain.entity.SysUser;
import com.example.demo.security.service.SysUserService;
import com.example.demo.security.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SysUserService userService;

    @PostMapping("/sign-up")
    public ResultUtil signUp(SysUser user) {
        userService.save(user);
        return ResultUtil.success(null);
    }


}
