package com.hj.tj.gohome.enums;

public enum ErrorMsgEnum {

    // 系统错误
    SYS_ERR(5000, "系统错误", "系统错误"),
    PARAM_ERROR(5001, "参数错误", "参数错误"),

    // 登陆错误
    GET_WX_UER_INFO_ERROR(5100, "微信获取用户信息异常", "微信获取用户信息异常"),
    ;

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 给我们自己看的错误信息
     */
    private String errorMsg;

    /**
     * 给用户看的信息
     */
    private String userMsg;

    private ErrorMsgEnum(Integer code, String errorMsg, String userMsg) {
        this.code = code;
        this.userMsg = userMsg;
        this.errorMsg = errorMsg;
    }

    public Integer getCode() {
        return code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public String getUserMsg() {
        return userMsg;
    }
}
