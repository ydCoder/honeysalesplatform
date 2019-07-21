package com.ydcoding.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.lly835.bestpay.utils.JsonUtil;
import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.exception.SaleException;
import com.ydcoding.service.OrderService;
import com.ydcoding.service.PayService;
import com.ydcoding.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-30 21:23
 **/
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl  bestPayService;

    private static final String ORDER_NAME = "向宇蜂园微信支付订单";
    @Autowired
    private OrderService orderService;


   // 发起支付文档
    /*PayRequest payRequest = new PayRequest();
      payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
      payRequest.setOrderId("123456");
      payRequest.setOrderName("微信公众账号支付订单");
      payRequest.setOrderAmount(0.01);
      payRequest.setOpenid("openid_xxxxxx");
      bestPayService.pay(payRequest);*/




    @Override
    public PayResponse create(Orderdto orderDto) {
        PayRequest payRequest = new PayRequest();

        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setOrderAmount(orderDto.getOrderTotal().doubleValue());
        payRequest.setOpenid(orderDto.getBuyerOpenid());

        System.out.println("【微信支付 payRequest】"+JsonUtil.toJson(payRequest));
//调用统一下单api 返回预付单信息

        PayResponse payResponse=bestPayService.pay(payRequest);
        System.out.println("【微信支付payResponse】"+JsonUtil.toJson(payResponse));

        return  payResponse;




    }




    @Override
    public PayResponse notify(String notifyData) {
        //1. 验证签名
        //2. 支付的状态
        //3. 支付金额
        //4. 支付人(下单人 == 支付人)

        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        System.out.println("【微信支付】异步通知, payResponse={}"+ JsonUtil.toJson(payResponse));

        //查询订单
        Orderdto orderDTO = orderService.findOne(payResponse.getOrderId());

        //判断订单是否存在

        if (orderDTO == null) {
            System.out.println("【微信支付】异步通知, 订单不存在, orderId={}"+ payResponse.getOrderId());
            throw new SaleException(ResultEnum.ORDER_NOT_EXIST);

        }

        if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderTotal().doubleValue())) {
              System.out.println("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}");

               throw new SaleException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
          }
        orderService.paid(orderDTO);
//
//       try{
//           Orderdto   orderDTO = orderService.findOne(payResponse.getOrderId());
//           //判断金额是否一致(0.10   0.1)
//           if (!MathUtil.equals(payResponse.getOrderAmount(), orderDTO.getOrderTotal().doubleValue())) {
//               System.out.println("【微信支付】异步通知, 订单金额不一致, orderId={}, 微信通知金额={}, 系统金额={}");
//
//               throw new SaleException(ResultEnum.WXPAY_NOTIFY_MONEY_VERIFY_ERROR);
//           }
//
//           //修改订单的支付状态
//           orderService.paid(orderDTO);}
//       catch (Exception e){
//           throw new SaleException(ResultEnum.ORDER_NOT_EXIST);
//       }
//



        return payResponse;
    }

    @Override
    public RefundResponse refund(Orderdto orderdto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderdto.getOrderId());
        refundRequest.setOrderAmount(orderdto.getOrderTotal().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        System.out.println("【微信退款】request={}"+ JsonUtil.toJson(refundRequest));

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        System.out.println("【微信退款】response={}"+JsonUtil.toJson(refundResponse));

        return refundResponse;
    }

}
