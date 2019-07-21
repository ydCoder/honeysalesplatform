package com.ydcoding.configuration;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-30 21:49
 **/
@Configuration
public class WeChatPayConfiguration {

    @Autowired
    private WeChatAccountConfiguration accountConfig;



    //微信公众账号支付配置
  /*  WxPayH5Config wxPayH5Config = new WxPayH5Config();
      wxPayH5Config.setAppId("xxxxx");
      wxPayH5Config.setAppSecret("xxxxxxxx");
      wxPayH5Config.setMchId("xxxxxx");
      wxPayH5Config.setMchKey("xxxxxxx");
      wxPayH5Config.setNotifyUrl("http://xxxxx");

    //支付类, 所有方法都在这个类里
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);*/

    @Bean
    public BestPayServiceImpl bestPayService() {
        //配置
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(accountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(accountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(accountConfig.getMchId());
        wxPayH5Config.setMchKey(accountConfig.getMchKey());
        wxPayH5Config.setKeyPath(accountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(accountConfig.getNotifyUrl());

        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config);
        return bestPayService;
    }



}