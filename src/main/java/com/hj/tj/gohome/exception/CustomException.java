package com.hj.tj.gohome.exception;

import com.hj.tj.gohome.enums.ErrorMsgEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tangj
 * @description
 * @since 2018/10/10 15:29
 */
@Getter
@Setter
public class CustomException extends RuntimeException {

    private ErrorMsgEnum errorMsgEnum;
    private static final long serialVersionUID = 1L;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(ErrorMsgEnum errorMsgEnum) {
        this.errorMsgEnum = errorMsgEnum;
    }

}
