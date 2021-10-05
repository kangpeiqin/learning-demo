package com.example.oauthServer.rest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kpq
 * @since 1.0.0
 */
@RestController
public class UserController {

    @GetMapping("user")
    public OAuth2User user(@AuthenticationPrincipal OAuth2User principal) {
        return principal;
    }
}
