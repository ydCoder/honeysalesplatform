package com.ydcoding.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @program: honeysalesplatform
 * @description  商品 （包含类目）
 * @author: ydcoding
 * @create: 2019-04-02 18:55
 **/
public class ProductVo {

    @JsonProperty("name")
    private  String categoryName;//对应是前端类目name

    @JsonProperty("type")
    private Integer categoryNo;

    @JsonProperty("products")
    private List<ProductInfoVo>   productInfoVoList;

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
        this.categoryNo = categoryNo;
    }

    public List<ProductInfoVo> getProductInfoVoList() {
        return productInfoVoList;
    }

    public void setProductInfoVoList(List<ProductInfoVo> productInfoVoList) {
        this.productInfoVoList = productInfoVoList;
    }
}
