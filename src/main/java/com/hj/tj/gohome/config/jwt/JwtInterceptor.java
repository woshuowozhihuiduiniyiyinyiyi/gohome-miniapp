package com.hj.tj.gohome.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 身份验证工具
 */
@Slf4j
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private TokenHelper tokenHelper;

    public static final String BEARER = "Bearer ";
    private static final String AUTHORIZED_USER_FROM = "from";
    private static final String AUTHORIZED_USER_ID = "userId";
    private static final String AUTHORIZED_USER_NICKNAME = "nickName";

    /**
     * 身份过滤验证
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isEmpty(authHeader)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        if (!authHeader.startsWith(BEARER)) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }

        final String token = authHeader.substring(7);

        try {
            Claims claims = tokenHelper.parser(token);
            request.setAttribute(AUTHORIZED_USER_FROM, claims.get("from"));
            request.setAttribute(AUTHORIZED_USER_ID, claims.get("uid"));
            request.setAttribute(AUTHORIZED_USER_NICKNAME, claims.get("nickName"));
        } catch (ExpiredJwtException e) {
            log.error("Auth code expired.authHeader:{}", authHeader);
            response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
            return false;
        } catch (UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            log.error("Auth token :{} is error", token, e);
            response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
            return false;
        }

        return true;
    }
}
