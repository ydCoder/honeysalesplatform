package com.ydcoding.emums;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-10 11:03
 **/
public enum ResultEnum {
    CATEGORY_REPEAT(-66,"类别重复"),
    PRODUCT_NOT_EXIST(-1,"商品不存在"),

    PRODUCT_STOCK__NOT_ENOUGH(-2,"所选商品缺货"),

    ORDER_NOT_EXIST(-3,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(-4,"订单详情不存在"),

    ORDER_STATUS_ERROR(-5,"订单状态不正确"),

    OREDR_UPDATE_FAIL(-6,"订单更新失败"),
    ORDER_DETAIL_EMPTY(-7,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(-8,"订单支付状态不正确"),

    PARAM_ERROR(-9,"参数不正确"),

    CART_ISNULL(-10,"购物车为空"),

    WECHAT_MP_ERROR(-11,"微信公众账号错误"),

    PRODUCT_STATUS_ERROR(-12,"商品状态异常"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),
    ;

    private Integer code;

    private  String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
