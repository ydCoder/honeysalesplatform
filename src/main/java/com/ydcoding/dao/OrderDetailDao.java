package com.ydcoding.dao;

import com.ydcoding.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-07 19:13
 **/
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {


    //查订单，先从order主表查询拿到订单id,再通过订单详情表的通过order_id查询，order表里一条记录可能对应detail多条记录

     List<OrderDetail> findByOrderId(String orderId);




}
