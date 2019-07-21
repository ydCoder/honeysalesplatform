package com.ydcoding.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @program: honeysalesplatform
 * @description   商品类别目
 * @author: ydcoding
 * @create: 2019-03-29 11:12
 * 表名与类名驼峰相对应
 **/
@Entity
@Data
public class ProductCategory {

    /*IDENTITY：主键由数据库自动生成（主要是自动增长型）这里必须写上完整的主键生成策略
    否则测试会有java.sql.SQLSyntaxErrorException: Table 'honeysale.hibernate_sequence' doesn't exist
    原因：在mysql数据库中，使用hibernate自动生成数据表的同时，数据库会自动生成hibernate_sequence表，
    如果删除该表，就会报错：could not read a hi value - you need to populate the table: hibernate_sequence
    */
    // 商品类目id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer categoryId;
    /* 商品类目名称*/
    private String categoryName;
    /* 商品类目编号*/
    private Integer categoryNo;

    //后台管理创建时间
    private Date createTime;

    //更新时间

    private  Date  updateTime;


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

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryNo) {
        this.categoryName = categoryName;
        this.categoryNo = categoryNo;
    }

    public ProductCategory(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(Integer categoryNo) {
        this.categoryNo= categoryNo;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ",categoryNo=" + categoryNo +
                '}';
    }
}
