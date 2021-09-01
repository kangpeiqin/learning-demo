package com.example.websocket.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author KPQ
 * @date 2021/9/1
 */
@Controller
public class IndexController {

    @GetMapping("index")
    public String getIndex() {
        return "index";
    }

}
