package com.ydcoding.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.ydcoding.dto.Orderdto;
import org.springframework.stereotype.Service;

/**
 * @program: honeysalesplatform
 * @description支付逻辑
 * @author: ydcoding
 * @create: 2019-04-30 21:20
 **/
@Service
public interface PayService {
    PayResponse create(Orderdto orderDto);
    PayResponse notify(String notifyData);

    RefundResponse refund(Orderdto orderdto);

}
