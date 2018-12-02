package com.hj.tj.gohome.vo.order;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrderSaveParam {

    /**
     * id
     */
    private Integer id;

    /**
     * 出发地
     */
    @NotBlank(message = "出发地不能为空")
    private String origin;

    /**
     * 目的地
     */
    @NotBlank(message = "目的地不能为空")
    private String destination;

    /**
     * 用记指定的出发日期
     */
    @NotBlank(message = "出发日期不能为空")
    private String expectDate;

    /**
     * 车次
     */
    @NotBlank(message = "车次不能为空")
    private String trainNumber;

    /**
     * 座位号
     */
    @NotBlank(message = "座位号不能为空")
    private String seat;

    /**
     * 乘车人列表
     */
    @NotEmpty(message = "乘车人不能为空")
    private List<Integer> passengerIdList;

    /**
     * 订单联系手机号
     */
    private String phone;

    /**
     * 是否接受上车补票，N不接受，Y接受
     */
    private String canBuyTicketLater = "N";
}
