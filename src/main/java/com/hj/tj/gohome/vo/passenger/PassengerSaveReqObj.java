package com.hj.tj.gohome.vo.passenger;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PassengerSaveReqObj {

    /**
     * id
     */
    private Integer id;

    /**
     * 旅客名称
     */
    @NotBlank(message = "乘客名称不能为空")
    private String name;

    /**
     * 乘客类型
     */
    @Min(value = 0, message = "乘客类型不正确")
    @Max(value = 2, message = "乘客类型不正确")
    private Integer type;

    /**
     * 证件类型
     */
    @Min(value = 0, message = "证件类型不正确")
    @Max(value = 3, message = "证件类型不正确")
    private Integer idCardType;

    /**
     * 身份证号
     */
    @NotBlank(message = "乘客证件号码不能为空")
    private String idCard;
}
