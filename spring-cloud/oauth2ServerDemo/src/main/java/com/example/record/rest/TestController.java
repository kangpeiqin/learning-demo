package com.example.record.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kpq
 * @since 1.0.0
 */
@Controller
public class TestController {

    @GetMapping("test")
    @PreAuthorize("hasRole('TEST')")
    @ResponseBody
    public String test() {
        return "test";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping({"/", "/index"})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        view.addObject("user", user);
        return view;
    }

}
