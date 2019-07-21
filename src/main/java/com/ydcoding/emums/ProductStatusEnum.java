package com.ydcoding.emums;

/**
 * @program: honeysalesplatform
 * @description  商品状态 用枚举类型表示
 * @author: ydcoding
 * @create: 2019-04-01 10:57
 **/
public enum ProductStatusEnum   implements  StatusEnum{

    SALE(0,"在售"),
    DOWN(1,"下架");


    private Integer status;//状态码

    private  String message;//状态码对应的信息

    ProductStatusEnum(Integer status, String message) {
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
