package com.ydcoding.util;

import java.util.Random;

/**
 * @program: honeysalesplatform
 * @description   //生成订单id以及订单详情的id工具类
 * @author: ydcoding
 * @create: 2019-04-11 21:24
 **/
public class IdUtil {
    //生成id的方法：时间+随机数
    public  static synchronized String  generateId(){

        Random random=new Random();

        long  time= System.currentTimeMillis();

        System.out.println(time);

         Integer number= random.nextInt(900000)+100000;
         return    time+String.valueOf(number);
    }

}
