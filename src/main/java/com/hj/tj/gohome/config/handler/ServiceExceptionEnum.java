package com.hj.tj.gohome.config.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ServiceExceptionEnum {
    /**
     * 系统错误
     */
    SYS_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误"),

    /**
     * 用户错误
     */
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "用户已经存在"),
    USER_NOT_EXISTS(HttpStatus.FORBIDDEN, "用户不存在"),
    USER_LOGIN_ERROR(HttpStatus.FORBIDDEN, "用户名或密码错误"),

    WX_QR_CODE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "生成微信二维码错误"),

    APP_TYPE_NOT_EXISTS(HttpStatus.FORBIDDEN, "app 类型不存在");

    /**
     * 错误码
     */
    private HttpStatus status;

    /**
     * 错误信息
     */
    private String message;

    private ServiceExceptionEnum(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}