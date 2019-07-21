package com.ydcoding.service.impl;


import com.ydcoding.dao.OrderDetailDao;
import com.ydcoding.dao.OrderInfoDao;

import com.ydcoding.dto.CartDto;
import com.ydcoding.dto.Orderdto;
import com.ydcoding.emums.OrderStatusEnum;
import com.ydcoding.emums.PayStatusEnum;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.entity.OrderDetail;
import com.ydcoding.entity.Product;
import com.ydcoding.exception.SaleException;
import com.ydcoding.service.OrderService;
import com.ydcoding.service.PayService;
import com.ydcoding.service.ProductService;
import com.ydcoding.transform.OrderInfoToOrderdto;
import com.ydcoding.util.IdUtil;
import com.ydcoding.entity.OrderInfo;
import com.ydcoding.wbsocketServer.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import java.util.stream.Collectors;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-09 16:49
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PayService payService;
    @Autowired
    private ProductService   productService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderInfoDao  orderDao;
    @Autowired
    private WebSocketServer webSocketServer;

    private static final String NEW_ORDER = "新订单";
    private static final String CANCEL_ORDER = "取消订单";

    @Override
    @Transactional
    public Orderdto create(Orderdto orderdto) {

        //创建订单id
        String  orderId= IdUtil.generateId();
        //总价格初始化
        BigDecimal orderTotal=new BigDecimal(BigInteger.ZERO);
        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail :  orderdto.getOrderDetails()) {
        Product product= productService.findOne(orderDetail.getProductId()).orElse(null);


            System.out.println("hahahahah"+product);


       //如果数据 不存在抛出异常
            if(product==null){
                    throw  new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            System.out.println("orderDTO:"+orderdto.getOrderDetails().get(0));
        //2.计算订单总价
            orderTotal=product.getProductPrice().
                    multiply(new BigDecimal(orderDetail.
                            getProductAmount())).
                    add(orderTotal);
            //订单详情入库
            orderDetail.setDetailId(IdUtil.generateId());
            //创建时就已经生成了
            orderDetail.setOrderId(orderId);
            //把product 的属性值拷贝到orderDetail中
            BeanUtils.copyProperties(product,orderDetail);
            System.out.println("test："+orderdto.getOrderDetails().get(0).getProductAmount());
//test：10
//           orderDetail.setProductAmount(1);手动设置
//            orderDetail.setProductAmount(orderdto.);
            orderDetailDao.save(orderDetail);
       }

    //3.写入订单数据库(orderInfo和orderdetail都要写数据)
        OrderInfo orderInfo=new OrderInfo();
        //...先设置再拷贝
        orderdto.setOrderId(orderId);

     BeanUtils.copyProperties(orderdto,orderInfo);
        orderInfo.setOrderId(orderId);
        orderInfo.setOrderTotal(orderTotal);
        orderInfo.setOrderStatus(OrderStatusEnum.NEW.getStatus());
        orderInfo.setPayStatus(PayStatusEnum.UNPAID.getStatus());



        orderDao.save(orderInfo);



        //4.下单成功后减库存操作
//        List<CartDto> cartDtoList =orderdto.getOrderDetails().stream().map(e->
//                new CartDto(e.getProductId(),e.get)
//        ).collect(Collectors.toList());Ordertail里的getProductId()

        List<CartDto> cartDtoList = orderdto.getOrderDetails().stream().map(e ->
                new CartDto(e.getProductId(),e.getProductAmount())
        ).collect(Collectors.toList());

        productService.minusStock(cartDtoList);
        //发送消息

    webSocketServer.sendMessage(NEW_ORDER);//新订单
        return orderdto;
    }

    @Override
    public Orderdto findOne(String orderId) {
      OrderInfo orderInfo=  orderDao.findById(orderId).orElse(null);
      if(orderInfo==null){
          throw  new SaleException(ResultEnum.ORDER_NOT_EXIST);
      }
        List<OrderDetail>   ordertailList=orderDetailDao.findByOrderId(orderId);
      if(CollectionUtils.isEmpty(ordertailList)){
          throw  new SaleException(ResultEnum.ORDERDETAIL_NOT_EXIST);
      }
        Orderdto  orderdto=new Orderdto();
        BeanUtils.copyProperties(orderInfo,orderdto);
        orderdto.setOrderDetails(ordertailList);
        return orderdto;
    }




    @Override
    public Page<Orderdto> findList(String buyerOpenid, Pageable pageable) {

       Page<OrderInfo> orderInfoPage= orderDao.findByBuyerOpenid(buyerOpenid,pageable);
        List<Orderdto> orderdtoList= OrderInfoToOrderdto.convert(orderInfoPage.getContent());

       Page<Orderdto> orderdtoPage=new PageImpl<Orderdto>(orderdtoList,pageable,orderInfoPage.getTotalElements());

        return orderdtoPage;
    }

    @Override
    @Transactional
    public Orderdto cancel(Orderdto orderdto) {

        //判断订单是否为新订单
        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            System.out.println("【取消订单】订单状态不正确！");
            throw  new  SaleException(ResultEnum.ORDER_STATUS_ERROR);

        }

        //修改订单状态
        OrderInfo orderInfo=new OrderInfo();
        orderdto.setOrderStatus(OrderStatusEnum.CANCEL.getStatus());
        BeanUtils.copyProperties(orderdto,orderInfo);

        OrderInfo  updateResult=orderDao.save(orderInfo);
        if(updateResult==null){
            System.out.println("【取消订单】更新失败！");

            throw  new SaleException(ResultEnum.OREDR_UPDATE_FAIL);
        }

        //返回库存
        if(CollectionUtils.isEmpty(orderdto.getOrderDetails())){

            System.out.println("【取消订单】订单中无商品信息！");
            throw  new  SaleException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto> cartDtoList = orderdto.getOrderDetails().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductAmount()))
                .collect(Collectors.toList());
        productService.addStock(cartDtoList);

        //如果支付,给用户退款

        if(orderdto.getPayStatus().equals(PayStatusEnum.PAID.getStatus())){
            //TODO
            payService.refund(orderdto);
            //修改订单支付状态

            orderdto.setPayStatus(PayStatusEnum.REFUND.getStatus());
            OrderInfo orderrefund=new OrderInfo();
            BeanUtils.copyProperties(orderdto, orderrefund);
            OrderInfo   orderRefund=orderDao.save( orderrefund);
            if( orderRefund==null){
                System.out.println("【订单退款完成】更新失败！");

                throw  new SaleException(ResultEnum.OREDR_UPDATE_FAIL);
            }
            webSocketServer.sendMessage(CANCEL_ORDER);//取消订单

        }

        return orderdto;
    }

    @Override
    @Transactional
    public Orderdto finish(Orderdto orderdto) {
        //判断订单状态

        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            System.out.println("【完结订单】订单状态不正确");
            throw  new SaleException(ResultEnum.ORDER_STATUS_ERROR);


        }
        //修改订单状态  完结
        orderdto.setOrderStatus(OrderStatusEnum.END.getStatus());
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderdto,orderInfo);
        OrderInfo  updateResult=orderDao.save(orderInfo);
        if(updateResult==null){
            System.out.println("【完结订单】更新失败！");

            throw  new SaleException(ResultEnum.OREDR_UPDATE_FAIL);
        }



        return orderdto;
    }

    @Override
    @Transactional
    public Orderdto paid(Orderdto orderdto) {
        //判断订单状态
        if(!orderdto.getOrderStatus().equals(OrderStatusEnum.NEW.getStatus())){
            System.out.println("【订单支付】订单状态不正确！");
            throw  new  SaleException(ResultEnum.ORDER_STATUS_ERROR);

        }

        //判断支付状态
        if(!orderdto.getPayStatus().equals(PayStatusEnum.UNPAID.getStatus())){
            System.out.println("【订单支付】订单支付状态不正确");
            throw  new  SaleException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改订单支付状态

        orderdto.setPayStatus(PayStatusEnum.PAID.getStatus());
        OrderInfo orderInfo=new OrderInfo();
        BeanUtils.copyProperties(orderdto,orderInfo);
        OrderInfo  updateResult=orderDao.save(orderInfo);
        if(updateResult==null){
            System.out.println("【订单支付完成】更新失败！");

            throw  new SaleException(ResultEnum.OREDR_UPDATE_FAIL);
        }

        return orderdto;
    }


    //后台管理员的查询列表
    @Override
    public Page<Orderdto> findList(Pageable pageable) {

        Page<OrderInfo> orderInfoPage=orderDao.findAll(pageable);


        List<Orderdto> orderdtoList= OrderInfoToOrderdto.convert(orderInfoPage.getContent());

        Page<Orderdto> orderdtoPage=new PageImpl<Orderdto>(orderdtoList,pageable,orderInfoPage.getTotalElements());

        return orderdtoPage;
    }

    @Override
    public List<OrderInfo> findOrderAllList() {
        return orderDao.findAll();
    }
}
