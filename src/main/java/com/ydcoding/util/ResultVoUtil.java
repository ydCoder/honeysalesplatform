package com.ydcoding.util;


import com.ydcoding.view.ResultVo;

/**
 * @program: honeysalesplatform
 * @description  生成最外层视图对象的工具类
 * @author: ydcoding
 * @create: 2019-04-02 22:02
 **/

public class ResultVoUtil {
    public  static ResultVo  success(Object obj){
        ResultVo resultVo=new ResultVo();
        //data  :  goods
        resultVo.setData(obj);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return  resultVo;

    }

    public  static ResultVo  success(){

        return  success(null);

    }
    public  static ResultVo  error(Integer code,String msg){
        ResultVo resultVo=new ResultVo();
        resultVo.setData(code);
       resultVo.setMsg(msg);
        return  resultVo;


    }
}
