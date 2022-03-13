package com.example.mall.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判断某个人是否有权限访问某个接口
 *
 * @author kpq
 * @since 1.0.0
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //可以访问当前资源的角色列表
        for (ConfigAttribute configAttribute : collection) {
            String role = configAttribute.getAttribute();
            if (SecurityMetaDataSource.ROLE_LOGIN.equals(role)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("尚未登录，请先登录");
                } else {
                    return;
                }
            }
            //获取当前登录人员的角色
            List<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            //对当前资源拥有权限，则放行通过
            if (authorities.contains(role)) {
                return;
            }
        }
        //对当前资源没有权限，抛出异常
        throw new AccessDeniedException("权限不足，请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
