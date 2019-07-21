package com.ydcoding.service.impl;

import com.ydcoding.dto.Orderdto;
import com.ydcoding.service.OrderService;
import com.ydcoding.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-30 22:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class PayServiceImplTest {

    @Autowired
    private PayService payService;
    @Autowired
    private OrderService orderService;
    @Test
    public void create() {

        Orderdto orderdto=orderService.findOne("1556791285799268696");
        payService.create(orderdto);


    }
    @Test
    public void refund() {
        Orderdto orderDTO = orderService.findOne("1558071732549509464");
        payService.refund(orderDTO);
    }

}