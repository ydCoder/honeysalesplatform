package com.ydcoding.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ydcoding.emums.ProductStatusEnum;
import com.ydcoding.util.EnumUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: honeysalesplatform
 * @description  商品信息
 * @author: ydcoding
 * @create: 2019-03-31 09:54
 **/
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    //名称
    private String productName;

    //价格 精度要求较高,用来对超过16位有效位的数进行精确的运算
    private BigDecimal productPrice;

    //图片:链接地址
    private String  imgAddr;

  //描述信息
    private  String productDesc;

    //数量
    private  Integer productStock;

    //所属类目编号：与类目表通过此类目编号相关联

    private  Integer categoryNo;


    //商品状态 0:在售 1:下架
    private  Integer status;



    //后台管理创建时间
    private Date createTime;

    //更新时间

    private  Date  updateTime;




    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByStatus(status, ProductStatusEnum.class);
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

    public Product() {
    }

    public Product(Integer productId, String productName, BigDecimal productPrice,
                   String imgAddr, String productDesc, Integer productStock,
                   Integer categoryNo, Integer status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imgAddr = imgAddr;
        this.productDesc = productDesc;
        this.productStock = productStock;
        this.categoryNo = categoryNo;

        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo = categoryNo;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", imgAddr='" + imgAddr + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productStock=" + productStock +
                ", categoryNo=" + categoryNo +
                ", status=" + status +
                '}';
    }
}
