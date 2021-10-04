package com.example.oauthServer.rest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
public class UserController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

}
