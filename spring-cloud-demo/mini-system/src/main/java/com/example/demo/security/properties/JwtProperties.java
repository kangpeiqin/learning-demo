package com.example.demo.security.properties;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

/**
 * @author KPQ
 * @date 2021/11/18
 */
@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtProperties {

    /**
     * Jwt 密钥
     */
    private String key;

    /**
     * token 过期时间
     */
    private Long expiration;

    /**
     * 获取加密后的密钥
     *
     * @return
     */
    public SecretKey getSecretKey() {
        final byte[] secretByte = DatatypeConverter.parseBase64Binary(key);
        return Keys.hmacShaKeyFor(secretByte);
    }

}
