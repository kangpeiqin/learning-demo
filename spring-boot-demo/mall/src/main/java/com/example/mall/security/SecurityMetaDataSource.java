package com.example.mall.security;

import com.example.mall.entity.Resource;
import com.example.mall.entity.Role;
import com.example.mall.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 根据用户传来的请求地址，分析出请求需要的角色
 *
 * @author kpq
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class SecurityMetaDataSource implements FilterInvocationSecurityMetadataSource {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final ResourceService resourceService;

    public static final String ROLE_LOGIN = "ROLE_LOGIN";

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取当前请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //获取所有的资源，并查询这些资源被那些角色所拥有
        List<Resource> resourceList = resourceService.getAllMenusWithRole();
        for (Resource resource : resourceList) {
            if (antPathMatcher.match(resource.getUrl(), requestUrl)) {
                //当前菜单资源被哪些角色所拥有
                String[] roleArr = resource.getRoles()
                        .stream().map(Role::getCode).toArray(String[]::new);
                return SecurityConfig.createList(roleArr);
            }
        }
        return SecurityConfig.createList(ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
