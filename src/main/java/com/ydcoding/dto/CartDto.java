package com.ydcoding.dto;

/**
 * @program: honeysalesplatform
 * @description  购物车传输对象
 * @author: ydcoding
 * @create: 2019-04-13 10:00
 **/
public class CartDto {

    //商品id
    private Integer productId;

    //数量

    private Integer productQuantity;

    public CartDto(Integer productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDto() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
