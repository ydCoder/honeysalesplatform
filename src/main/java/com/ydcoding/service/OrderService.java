package com.ydcoding.service;

import com.ydcoding.dao.OrderInfoDao;
import
        com.ydcoding.dto.Orderdto;
import com.ydcoding.entity.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-09 15:54
 **/
public interface OrderService {

    //创建订单
     Orderdto create(Orderdto orderdto);

    //查询订单(单个)
    Orderdto findOne(String orderId);
    //查询某个人的订单列表
    Page<Orderdto> findList(String buyerOpenid, Pageable pageable);
    //取消订单
     Orderdto cancel(Orderdto orderdto);
    //完成订单
    Orderdto finish(Orderdto orderdto);

    //支付订单

    Orderdto paid(Orderdto orderdto);

    //后台管理员查询所有订单列表(分页)
    Page<Orderdto> findList(Pageable pageable);

    //后台管理员查询所有订单列表(单页)

    List<OrderInfo>  findOrderAllList();

}
