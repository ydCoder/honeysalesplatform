package com.ydcoding.dao;

import com.ydcoding.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-31 10:44
 **/
// JpaRepository  里的参数是类名和主键类型 此处id 为Integer
 //findByStatus  jpa  用法
public interface ProductDao extends JpaRepository<Product,Integer> {

    //通过商品状态查询当前在售蜂产品
    List<Product> findByStatus(Integer status);



}
