package com.ydcoding.service;

import com.ydcoding.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-30 18:16
 **/
public interface ProductCategoryService {

    //通过id查询类目信息
    Optional<ProductCategory> findOne(Integer categoryId);

    //查询所有
   List<ProductCategory> findAll();

   //以上两个接口方法提供给商家后台管理使用

    //买家接口方法：一次性通过所有类目编号查询所有类目列表
    List<ProductCategory> findByCategoryNoIn(List<Integer> categoryTypeList);

    //新增和更新统一用save()
    ProductCategory save(ProductCategory productCategory);


    //查询类目是否已经存在
    ProductCategory findByCategoryNo(Integer categoryNo);
}
