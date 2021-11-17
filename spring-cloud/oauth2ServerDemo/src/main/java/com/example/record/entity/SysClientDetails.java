package com.example.record.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author KPQ
 * @date 2021/11/17
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_client_details")
@Accessors(chain = true)
@Data
public class SysClientDetails extends BaseEntity implements ClientDetails {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 资源服务器名称
     */
    private String resourceIds;

    /**
     * 授权域：READ,WRITE
     */
    private String scopes;

    /**
     * 授权类型：授权码模式(authorization_code)、密码模式(password)
     */
    private String grantTypes;

    /**
     * 重定向地址，授权码授权时时必填
     */
    private String redirectUrl;

    /**
     * 授权信息
     */
    private String authorizations;

    /**
     * 授权令牌有效时间
     */
    private Integer accessTokenValiditySeconds;

    /**
     * 刷新令牌有效时间
     */
    private Integer refreshTokenValiditySeconds;

    /**
     * 自动授权请求域
     */
    private String autoApproveScopes;

    /**
     * 是否需要密钥
     */
    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public boolean isScoped() {
        return StringUtils.hasLength(this.scopes);
    }

    @Override
    public Set<String> getResourceIds() {
        return stringToSet(resourceIds);
    }

    /**
     * 获取授权域
     */
    @Override
    public Set<String> getScope() {
        return stringToSet(scopes);
    }

    /**
     * 获取授权类型
     */
    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return stringToSet(grantTypes);
    }

    /**
     * 回调地址获取
     */
    @Override
    public Set<String> getRegisteredRedirectUri() {
        return stringToSet(redirectUrl);
    }

    /**
     * 客户端所有的权限，已经有 scope 的存在可以很好的对客户端的权限进行认证了
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * 判断是否自动授权
     */
    @Override
    public boolean isAutoApprove(String scope) {
        if (!StringUtils.hasLength(autoApproveScopes)) {
            return false;
        }
        Set<String> authorizationSet = stringToSet(authorizations);
        for (String auto : authorizationSet) {
            if ("true".equalsIgnoreCase(auto) || scope.matches(auto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否需要添加额外的信息
     */
    @Override
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

    private Set<String> stringToSet(String s) {
        return Arrays.stream(s.split(",")).collect(Collectors.toSet());
    }
}
