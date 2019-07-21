package com.ydcoding.dao;

import com.ydcoding.entity.Admin;
import com.ydcoding.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-29 16:46
 **/

//继承标记接口JpaRepository，必须给出类名和主键类型
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    /* 接口：一次性通过所有类目编号查询所有类目列表
       参数：类目编号的集合
       返回值：所有类目信息的集合
     */
    //findByCategoryNoIn()方法    springboot封装find+方法名+In  对应数据库in的条件查询
    List<ProductCategory> findByCategoryNoIn(List<Integer> categoryTypeList);


    //List<ProductCategory> findAll();  自定义方法稍后研究
    ProductCategory findBycategoryNo(Integer categoryNo);
}
