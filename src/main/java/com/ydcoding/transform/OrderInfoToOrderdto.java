package com.ydcoding.transform;

import com.ydcoding.dto.Orderdto;
import com.ydcoding.entity.OrderInfo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-18 20:14
 **/
public class OrderInfoToOrderdto {
    public static Orderdto  convert(OrderInfo orderInfo){
        Orderdto orderdto=new Orderdto();

        BeanUtils.copyProperties(orderInfo,orderdto);
        return  orderdto;
    }

    public  static List<Orderdto> convert(List<OrderInfo> orderInfoList)
    {
      return   orderInfoList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }
}
