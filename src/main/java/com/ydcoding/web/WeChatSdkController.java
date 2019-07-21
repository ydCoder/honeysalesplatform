package com.ydcoding.web;

import com.ydcoding.configuration.ProjectUrlConfiguration;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.exception.SaleException;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.net.URLEncoder;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-29 10:24
 **/

@Controller
@RequestMapping("/wechat")
public class WeChatSdkController {
    @Autowired
    private WxMpService  wxMpService;

    @Autowired
    private ProjectUrlConfiguration projectUrlConfiguration;
    @RequestMapping("/authorize")
    public String  authorize(@RequestParam(value="returnUrl",required = false)  String returnUrl){

        //配置

        // String url="http://pyd888.natapp1.cc/sell/wechat/userinfo";
        String url = projectUrlConfiguration.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        //要重定向的url地址，  用户同意授权，获取code
        String redirectUrl=wxMpService.oauth2buildAuthorizationUrl(url,
                WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:"+redirectUrl;

    }
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam(value="state",required = false) String returnUrl){
        //通过code换取网页授权access_token 值
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {

            throw new SaleException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }

        String openId = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openId;

    }
}
