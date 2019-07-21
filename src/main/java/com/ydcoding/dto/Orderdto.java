package com.ydcoding.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.ydcoding.emums.OrderStatusEnum;
import com.ydcoding.emums.PayStatusEnum;
import com.ydcoding.entity.OrderDetail;
import com.ydcoding.util.DateToLongUtil;
import com.ydcoding.util.EnumUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-09 16:34
 **/

public class Orderdto {
    private  String  orderId;

    //买家的openid  openid是微信用户在公众号appid下的唯一用户标识（appid不同，则获取到的openid就不同）

    private String buyerOpenid;

    //买家名字
    private  String buyerName;

    //买家手机号
    private  String  buyerPhone;

    //买家地址
    private String  buyerAddr;

    //订单总价格

    private BigDecimal orderTotal;

    //订单状态
    private Integer orderStatus;//默认为新订单
    //支付状态 默认未支付
    private  Integer  payStatus;//默认为未支付

    //创建时间
    @JsonSerialize(using= DateToLongUtil.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using= DateToLongUtil.class)
    private  Date  updateTime;

    List<OrderDetail>   orderDetails;//  订单信息表与订单详情表是一对多的关系 用集合表示

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerOpenid() {
        return buyerOpenid;
    }

    public void setBuyerOpenid(String buyerOpenid) {
        this.buyerOpenid = buyerOpenid;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }






    //后台获取订单状态用
    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByStatus(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByStatus(payStatus, PayStatusEnum.class);
    }



}
