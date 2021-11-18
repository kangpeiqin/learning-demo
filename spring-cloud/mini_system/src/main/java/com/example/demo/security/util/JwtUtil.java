package com.example.demo.security.util;

import com.example.demo.security.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author KPQ
 * @date 2021/11/18
 */
public class JwtUtil {

    public static String generateToken(Long id, String username, Long expiration, SecretKey secretKey) {
        Date generateDate = new Date();
        final Date expirationDate = new Date(generateDate.getTime() + expiration * 1000 * 3600);
        String token = Jwts.builder()
                .setId(String.valueOf(id))
                .setHeaderParam("type", "JWT")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuer("admin")
                .setIssuedAt(generateDate)
                .setSubject(username)
                .setExpiration(expirationDate)
                .compact();
        return SecurityConstants.BEARER + token;
    }

    /**
     * 通过token获取登录凭证
     *
     * @param token
     * @param secretKey
     * @return
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token, SecretKey secretKey) {
        Claims claims = getClaims(token, secretKey);
        String userName = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(userName, token, null);
    }

    /**
     * 解析token,获取身份凭证
     *
     * @param token
     * @param secretKey
     * @return
     */
    public static Claims getClaims(String token, SecretKey secretKey) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }


    public static void main(String[] args) {
        //利用HMAC-SHA算法生成一个随机密钥
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }

}
