package com.ydcoding.transform;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.entity.OrderDetail;
import com.ydcoding.exception.SaleException;
import com.ydcoding.ordervalidate.OrderForm;


import java.util.ArrayList;
import java.util.List;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-26 14:14
 **/
public class OrderForm2OrderDto {

    public static Orderdto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        Orderdto orderDto = new Orderdto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddr(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            System.out.println("【对象转换】错误");
            throw new SaleException(ResultEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetails(orderDetailList);

        return orderDto;
    }

}
