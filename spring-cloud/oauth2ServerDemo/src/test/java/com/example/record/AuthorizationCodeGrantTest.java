package com.example.record;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.record.AuthorizationServerInfo.getUrl;
import static org.junit.jupiter.api.Assertions.*;

class AuthorizationCodeGrantTest {


    private AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
    private AuthorizationServerInfo authorizationServerInfo = new AuthorizationServerInfo();


    @BeforeEach
    void setUp() {
        //授权码获取地址
        resource.setUserAuthorizationUri(getUrl("/oauth/authorize"));
        //令牌获取地址
        resource.setAccessTokenUri(getUrl("/oauth/token"));
        resource.setClientId("oauth2");
        resource.setId("oauth2");
        resource.setScope(Arrays.asList("READ", "WRITE"));
    }

    @Test
    void testCannotConnectWithoutToken() {
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
        assertThrows(UserRedirectRequiredException.class, () -> template.getForObject(getUrl("/oauth/me"), String.class));
    }

    @Test
    void testCodeAcquisitionWithCorrectContext() {
        ResponseEntity<String> page = authorizationServerInfo.getForString("/login");
        //获取Cookie
        String cookie = page.getHeaders().getFirst("Set-Cookie");
        System.out.println(cookie);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*").matcher(page.getBody());
        assertTrue(matcher.find());
        // 2. 添加表单数据
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("username", "admin");
        form.add("password", "123456");
        form.add("_csrf", matcher.group(1));
        // 3. 登录授权并获取登录成功的 cookie
        ResponseEntity<Void> response = authorizationServerInfo.postForStatus("/login", headers, form);
        assertNotNull(response);
        cookie = response.getHeaders().getFirst("Set-Cookie");
        headers = new HttpHeaders();
        headers.set("Cookie", cookie);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        // 4. 获取授权码
        ResponseEntity<String> confirm = authorizationServerInfo.getForString("/oauth/authorize?response_type=code&client_id=oauth2&redirect_uri=http://example.com&scope=READ");
        headers = confirm.getHeaders();
        if (!confirm.getStatusCode().is3xxRedirection()) {
            assertNotNull(confirm.getBody());
            Matcher matcherConfirm = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*").matcher(confirm.getBody());
            assertTrue(matcherConfirm.find());
            headers = new HttpHeaders();
            headers.set("Cookie", cookie);
            headers.setAccept(Collections.singletonList(MediaType.ALL));

            // 5. 构建 同意授权 的表单
            form = new LinkedMultiValueMap<>();
            form.add("user_oauth_approval", "true");
            form.add("scope.READ", "true");
            form.add("_csrf", matcherConfirm.group(1));

            // 6. 请求授权，获取 授权码
            headers = authorizationServerInfo.postForHeaders("/oauth/authorize", form, headers);
        }
        System.out.println(headers);
    }
}
