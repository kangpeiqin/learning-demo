package com.example.record;

import org.junit.jupiter.api.Test;

import java.util.Base64;

class AuthorizationServerTest {

    @Test
    void base64EncodeTest() {
        System.out.println(Base64.getEncoder().encodeToString("example:oauth2".getBytes()));
    }

}
