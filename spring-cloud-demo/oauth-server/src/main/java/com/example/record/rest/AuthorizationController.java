package com.example.record.rest;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author kpq
 * @since 1.0.0
 */
@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizationController {

    /**
     * 自定义确认授权页面，
     * 也可以使用 {@link AuthorizationEndpoint # setUserApprovalPage(String)} 方法
     *
     *
     * @param model
     * @return
     */
    @GetMapping("/oauth/confirm_access")
    public ModelAndView getAccessConfirmation(Map<String, Object> model) {
        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        ModelAndView view = new ModelAndView();
        view.setViewName("authorization");
        view.addObject("clientId", authorizationRequest.getClientId());
        // 传递 scope 过去,Set 集合
        view.addObject("scopes", authorizationRequest.getScope());
        return view;
    }
}
