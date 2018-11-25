package com.hj.tj.gohome.vo.login;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WxLoginReqObj {

    private String code;

    private String encryptedData;

    private String iv;
}
