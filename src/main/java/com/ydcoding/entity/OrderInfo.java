package com.ydcoding.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ydcoding.emums.OrderStatusEnum;
import com.ydcoding.emums.PayStatusEnum;
import com.ydcoding.util.EnumUtil;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: honeysalesplatform
 * @description  订单表：订单状态 支付状态
 * @author: ydcoding
 * @create: 2019-04-03 18:56
 **/
@Entity
@DynamicUpdate
public class OrderInfo {



    //订单id
    //订单id
    @Id
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

    private BigDecimal  orderTotal;

    //订单状态
    private Integer orderStatus= OrderStatusEnum.NEW.getStatus();//默认为新订单
    //支付状态 默认未支付
    private  Integer  payStatus= PayStatusEnum.UNPAID.getStatus();//默认为未支付

    //创建时间
    private Date createTime;

    //更新时间

    private  Date  updateTime;


    public OrderInfo() {
    }
    //有参数构造方便单元测试
    public OrderInfo(String orderId, String buyerOpenid,
                 String buyerName,String buyerPhone,
                 String buyerAddr, BigDecimal orderTotal) {
        this.orderId = orderId;
        this.buyerOpenid = buyerOpenid;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddr = buyerAddr;
        this.orderTotal = orderTotal;
    }

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
