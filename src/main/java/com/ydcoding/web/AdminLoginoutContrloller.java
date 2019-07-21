package com.ydcoding.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ydcoding.entity.Admin;
import com.ydcoding.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-10 11:01
 **/
@Controller
@RequestMapping("/admin")
public class AdminLoginoutContrloller {
    @Autowired
    private  AdminService adminService;

    @RequestMapping("/login")
    public ModelAndView   login(){
        return new ModelAndView("login/login");
    }

    @RequestMapping("/main")
    public ModelAndView   main(HttpServletRequest httpServletRequest){
        if( httpServletRequest.getSession().getAttribute("adminName")==null){
            return new ModelAndView("login/login");
        }
        return new ModelAndView("admin/index");
    }

   @Autowired
    private DefaultKaptcha defaultKaptcha;
//生成验证码
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse)
            throws Exception {
            byte[] captchaChallengeAsJpeg = null;
            ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);

        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequestMapping(value = "/login/code", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> vrifyCode(@RequestParam("code")  String code,  HttpSession httpSession) {
        Map<String,Object> map=new HashMap<>();
        String serverCode = (String) httpSession.getAttribute("vrifyCode");
        if(code.equals(serverCode)){
            map.put("result","success");
           return  map;
        }
        map.put("result","error");
        return map;

    }

//登录核心代码
    @PostMapping("/login")
    @ResponseBody
    public Map<String,Object>login(String adminEmail,String password,HttpServletRequest httpServletRequest) {
         //1.判断用户是否存在
        Admin userResult= adminService.findAdmin(adminEmail, DigestUtils.md5DigestAsHex(password.getBytes()));
        Map<String,Object> map=new HashMap<>();
         //2.返回结果
            if(userResult!=null){
                    httpServletRequest.getSession().setAttribute("adminName", userResult.getAdminName());
                    map.put("result","success");
                    return  map;
                }
                    map.put("result","用户名或密码不正确");
                    return map;
    }
//注销
@RequestMapping("/logout")
    public  ModelAndView logout(HttpServletRequest httpServletRequest){
       httpServletRequest.getSession().removeAttribute("adminName");
       return new ModelAndView("login/login");
    }
}
