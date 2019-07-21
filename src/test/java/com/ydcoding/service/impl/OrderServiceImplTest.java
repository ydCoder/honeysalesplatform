package com.ydcoding.service.impl;

import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.OrderStatusEnum;
import com.ydcoding.emums.PayStatusEnum;
import com.ydcoding.entity.OrderDetail;
import com.ydcoding.entity.OrderInfo;
import com.ydcoding.service.OrderService;
import com.ydcoding.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-13 10:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {



    @Autowired
    private OrderService orderService;

    private  final  String  buyerOpenid="eretfewqwee";

    private  final  String  orderId="166668888";

    @Test
    public void create() {




        Orderdto orderdto=new Orderdto();
        orderdto.setBuyerName("suse");
        orderdto.setBuyerAddr("四川宜宾翠屏区");
        orderdto.setBuyerPhone("18882020596");
        orderdto.setBuyerOpenid(buyerOpenid);


        //购物车
        List<OrderDetail>  orderDetailList=new ArrayList<>();

        OrderDetail  o1=new OrderDetail();
        o1.setProductId(1);
        o1.setProductAmount(1);
        orderDetailList.add(o1);

        OrderDetail  o2=new OrderDetail();
        o2.setProductId(3);
        o2.setProductAmount(1);
        orderDetailList.add(o2);

        orderdto.setOrderDetails(orderDetailList);

                Orderdto  result = orderService.create(orderdto);


        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() {

        Orderdto  result=orderService.findOne(orderId);

        Assert.assertEquals(orderId,result.getOrderId());
    }

    @Test
    public void findList() {

        PageRequest  request=PageRequest.of(0,2);
    Page<Orderdto>  orderdtoPage= orderService.findList(buyerOpenid,request);
    Assert.assertNotEquals(0,orderdtoPage.getTotalElements());
    }

    @Test
    public void cancel() {

        Orderdto  orderdto=orderService.findOne(orderId);
        Orderdto  result=orderService.cancel(orderdto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getStatus(),result.getOrderStatus());
    }

    @Test
    public void finish() {

        Orderdto  orderdto=orderService.findOne(orderId);
        Orderdto  result=orderService.finish(orderdto);
        Assert.assertEquals(OrderStatusEnum.END.getStatus(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        Orderdto  orderdto=orderService.findOne(orderId);
        Orderdto  result=orderService.paid(orderdto);
        Assert.assertEquals(PayStatusEnum.PAID.getStatus(),result.getPayStatus());


    }

    //后台查询所有订单列表
    @Test
    public void findAll(){
        PageRequest  request=PageRequest.of(0,2);
        Page<Orderdto>  orderdtoPage=orderService.findList(request);

        System.out.println("列表："+ JsonUtil.toJson(orderdtoPage.getContent()));
        Assert.assertNotEquals(0,orderdtoPage.getTotalElements());
    }

    @Test
    public void findOrderAllList(){
       List<OrderInfo> list=orderService.findOrderAllList();
        System.out.println("列表："+ JsonUtil.toJson(list));

        Assert.assertNotEquals(0,list.size());
    }
}