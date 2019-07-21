package com.ydcoding.dao;

import com.ydcoding.entity.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-04 15:22
 **/
public interface OrderInfoDao  extends JpaRepository<OrderInfo,String> {
    //按照买家的openid订单查询出来并分页

    Page<OrderInfo>  findByBuyerOpenid(String buyerOpenid, Pageable pageable);

//    //查询所有商品
//    List<OrderInfo>   findAll();


//有findAll方法 默认实现
}
