package com.hj.tj.gohome.service;

import com.hj.tj.gohome.vo.login.WxLoginReqObj;
import com.hj.tj.gohome.vo.login.WxLoginResObj;

public interface OwnerService {

    /**
     * 微信登录
     *
     * @param wxLoginReqObj
     * @return
     */
    WxLoginResObj login(WxLoginReqObj wxLoginReqObj) throws Exception;

    String refreshToken(String code) throws Exception;
}
