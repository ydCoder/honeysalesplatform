package com.ydcoding.web;


import com.ydcoding.dto.Orderdto;


import com.ydcoding.entity.OrderInfo;
import com.ydcoding.service.OrderService;

import com.ydcoding.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description 后台管理订单
 * @author: ydcoding
 * @create: 2019-02-02 09:42
 **/
@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    /*********订单相关*******/
    //订单列表（所有）..方便下载文件
    @RequestMapping("/order/orderall")
    public ModelAndView orderAll(Map<String,List<OrderInfo>> orderMap,HttpServletRequest httpServletRequest){
        if( httpServletRequest.getSession().getAttribute("adminName")==null){
            return new ModelAndView("login/login");
        }

            List<OrderInfo> orderList=orderService.findOrderAllList();
            System.out.println("列表"+JsonUtil.toJson(orderList));
            orderMap.put("orderList",orderList);

            return  new ModelAndView("admin/orderall",orderMap);
    }

    //Pageable从第0页开始查询的  订单列表 （分页）方便在线查看
    //分页查询订单列表
    @RequestMapping("/order/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1")  Integer page,
                             @RequestParam(value="size",defaultValue = "10") Integer size,
                             Map<String,Object> orderMap, HttpServletRequest httpServletRequest){
        if( httpServletRequest.getSession().getAttribute("adminName")==null){
            return new ModelAndView("login/login");
        }
        //注意细节page-1
        PageRequest orderList=PageRequest.of(page-1,size);
        Page<Orderdto> orderdtoPage=orderService.findList(orderList);
        orderMap.put("orderdtoPage",orderdtoPage);
        orderMap.put("nowPage",page);
         //  orderdtoPage.getContent()  获取数据
        System.out.println("订单列表："+ JsonUtil.toJson(orderdtoPage.getContent()));
        return  new ModelAndView("admin/orderlist",orderMap);
    }

    //管理员取消订单
    @GetMapping("/order/cancel")
    @ResponseBody
    public  Orderdto cancel(@RequestParam("orderId") String orderId){
     Orderdto orderdto= orderService.findOne(orderId);
     //TODO
       return   orderService.cancel(orderdto);
    }
    //订单详情
    @GetMapping("/order/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> orderMap) {
        Orderdto orderDto = new Orderdto();
        orderDto = orderService.findOne(orderId);
        System.out.println("此订单各记录数"+orderDto.getOrderDetails().size());
        int count=orderDto.getOrderDetails().size();
        orderMap.put("orderDto", orderDto);
        orderMap.put("count", count);

        return new ModelAndView("admin/orderdetail",orderMap);
    }
//完结订单

    @RequestMapping("/order/finish")
    @ResponseBody
    public   Orderdto finished(@RequestParam("orderId") String orderId) {
             Orderdto orderDto = orderService.findOne(orderId);
             System.out.println("要完结的订单为："+JsonUtil.toJson(orderDto));
            Orderdto orderdto= orderService.finish(orderDto);
            return  orderdto;
    }

}
