package com.ydcoding.exception;

import com.ydcoding.emums.ResultEnum;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-10 11:01
 **/
public class SaleException  extends  RuntimeException{

    private  Integer code;

    public SaleException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();

    }

    public SaleException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
