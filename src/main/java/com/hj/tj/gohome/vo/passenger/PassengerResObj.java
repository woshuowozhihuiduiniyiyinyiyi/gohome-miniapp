package com.hj.tj.gohome.vo.passenger;

import lombok.Data;

@Data
public class PassengerResObj {

    /**
     * 乘客id
     */
    private Integer id;

    /**
     * 乘客名称
     */
    private String name;

    /**
     * 乘客类型
     */
    private String type;

    /**
     * 乘客证件类型
     */
    private String idCardType;

    /**
     * 乘客证件
     */
    private String idCard;

}
