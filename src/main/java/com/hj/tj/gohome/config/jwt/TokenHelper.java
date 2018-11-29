package com.hj.tj.gohome.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "auth.jwt")
public class TokenHelper {
    /**
     * 加密秘钥
     */
    private String secret;

    /**
     * 有效时间
     */
    private long expire;

    /**
     * 用户凭证
     */
    private String header;

    /**
     * 生成Token签名
     *
     * @param userId 用户ID
     * @return 签名
     */
    public String generate(long userId, String from, String nickName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userId);
        claims.put("from", from);
        claims.put("nickName", nickName);

        long nowMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setId(String.valueOf(userId))
                .setClaims(claims)
                .setSubject("auth")
                .signWith(SignatureAlgorithm.HS512, getSecret())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + getExpire() * 1000))
                .compact();
    }

    /**
     * 解析Token
     *
     * @param token 签名
     * @return 签名信息
     */
    public Claims parser(String token) {
       return Jwts.parser()
                .setSigningKey(getSecret())
                .parseClaimsJws(token).getBody();
    }
}


