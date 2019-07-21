package com.ydcoding.emums;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-03 20:30
 **/
public enum PayStatusEnum  implements StatusEnum {

    UNPAID(0,"未支付 "),

    PAID(1,"已支付"),
    REFUND(-1,"已退款")

    ;


    private Integer status;//状态码

    private  String message;//状态码对应的信息

    PayStatusEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
