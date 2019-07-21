package com.ydcoding.emums;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-03 20:17
 **/
public enum OrderStatusEnum implements StatusEnum {
    NEW(0,"新订单"),
    END(1,"已发货"),
    CANCEL(2,"已取消"),
    WAIT(3,"待发货"),

    ;


    private Integer status;//状态码

    private  String message;//状态码对应的信息

    OrderStatusEnum(Integer status, String message) {
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
