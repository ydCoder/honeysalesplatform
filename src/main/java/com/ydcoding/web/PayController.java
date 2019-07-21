package com.ydcoding.web;

import com.lly835.bestpay.model.PayResponse;
import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.exception.SaleException;
import com.ydcoding.service.OrderService;
import com.ydcoding.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-30 21:14
 **/
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                            Map<String,Object>  map) {
        //查询订单
        Orderdto orderDto = orderService.findOne(orderId);
        if (orderDto == null) {
            throw new SaleException(ResultEnum.ORDER_NOT_EXIST);
        }

        //发起支付
       PayResponse payResponse= payService.create(orderDto);
       map.put("payResponse",payResponse);
       map.put("returnUrl",returnUrl);
     return  new ModelAndView("pay/create",map);

    }


    /**
     * 微信异步通知
     * @param notifyData
     */
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {
        payService.notify(notifyData);

        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }


}
