package com.ydcoding.service;

import com.ydcoding.dto.CartDto;
import com.ydcoding.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-01 10:10
 **/
public interface ProductService {

    Product save(Product product);

    //根据商品id查询商品
    Optional<Product> findOne(Integer productId);



    //前台查询所有在售商品
    List<Product> findSaleAll();


    //加库存

    void  addStock(List<CartDto> cartDtoList);

    //减库存

    void  minusStock(List<CartDto> cartDtoList);



    //后台管理员查询所用商品并分页显示
    Page<Product> findAll(Pageable pageable);


    //上架

    Product upSale(Integer productId);

    //下架
    Product  downSale(Integer productId);



}
