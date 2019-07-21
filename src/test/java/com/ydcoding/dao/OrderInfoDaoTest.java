package com.ydcoding.dao;

import com.ydcoding.entity.OrderInfo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description  订单主表测试类
 * @author: ydcoding
 * @create: 2019-04-07 19:23
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderInfoDaoTest {

    @Autowired
    private OrderInfoDao orderDao;

    private  final String buyerOpenid="ydcoding666";

    @Test
    public  void saveTest()
    {

      OrderInfo  order = new OrderInfo("YD190408",buyerOpenid,
    "ydsuse", "18882020596",
              "四川轻化工大学(宜宾校区)二期器美园B8",
              new BigDecimal(6.6));

              OrderInfo rs=orderDao.save(order);
              Assert.assertNotNull(rs);//表示不为空就测试通过
    }

    @Test
    public void findByBuyerOpenid() {
        //定义的是Pageable,用的是他的实现类PageRequest
        PageRequest request=new PageRequest(0,1);//从第几页开始查询几项

        Page<OrderInfo>  orderInfo=orderDao.findByBuyerOpenid(buyerOpenid,request);

        System.out.println(orderInfo.getTotalElements());

//        buyerName = "ydsuse"
//        buyerPhone = "18882020596"
//        buyerAddr = "四川轻化工大学(宜宾校区)二期器美园B8"
//        orderTotal = {BigDecimal@9129} "168.60"
//        orderStatus = {Integer@9130} 0
//        payStatus = {Integer@9130} 0
//        createTime = {Timestamp@9131} "2019-04-09 19:52:29.0"
//        updateTime = {Timestamp@9132} "2019-04-09 19:52:29.0"

    }
}