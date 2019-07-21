package com.ydcoding.dao;

import com.ydcoding.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-09 06:56
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {

    @Autowired
    private  OrderDetailDao orderDetailDao;


    @Test
    public  void  saveTest(){
        OrderDetail  orderDetail=new OrderDetail("1234568","YD0409",
                2,"枸杞蜜",new BigDecimal(88.8),
                1,"http://xyfy");

                 OrderDetail result=orderDetailDao.save(orderDetail);
                 Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails= orderDetailDao.findByOrderId("123456");
        Assert.assertNotNull(orderDetails);

    }
}