package com.ydcoding.web;

import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.exception.SaleException;
import com.ydcoding.ordervalidate.OrderForm;
import com.ydcoding.service.OrderService;
import com.ydcoding.transform.OrderForm2OrderDto;
import com.ydcoding.util.ResultVoUtil;
import com.ydcoding.view.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-25 19:52
 **/
@RestController
@RequestMapping("/shopper/order")
public class ShopperOrderController {

    @Autowired
    private OrderService orderService;
    //创建订单
    @RequestMapping("/create")
    public ResultVo<Map<String,String>> create(@Valid OrderForm orderForm,
                                               BindingResult bindingResult){
        //判断一下表单校验以后是否有错误
        if(bindingResult.hasErrors()){
            System.out.println("【创建订单】参数错误");
            throw  new SaleException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError()
            .getDefaultMessage());
        }
        Orderdto orderdto= OrderForm2OrderDto.convert(orderForm);

        if(CollectionUtils.isEmpty(orderdto.getOrderDetails())){
            System.out.println("【创建订单】购物车不能为空");
            throw new SaleException(ResultEnum.CART_ISNULL);
        }

   Orderdto createResult=     orderService.create(orderdto);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtil.success(map);
    }

    //订单列表


    @GetMapping("/list")
    public ResultVo<List<Orderdto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            System.out.println("【查询订单列表】openid为空");
            throw new SaleException(ResultEnum.PARAM_ERROR);
        }

        PageRequest request = PageRequest.of(page, size);
        Page<Orderdto> orderDtoPage = orderService.findList(openid, request);
       return ResultVoUtil.success(orderDtoPage.getContent());

    }
    //订单详情
    @GetMapping("/detail")
    public ResultVo<Orderdto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO  待安全校验
        Orderdto orderDto = orderService.findOne(orderId);
        return ResultVoUtil.success(orderDto);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        //TODO  待安全校验
      Orderdto  orderDto = orderService.findOne(orderId);
       orderService.cancel(orderDto);
       return  ResultVoUtil.success();
    }
}
