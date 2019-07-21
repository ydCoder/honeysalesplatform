package com.ydcoding.view;

/**
 * @program: honeysalesplatform
 * @description   返回给前端的视图对象的最外层对象数据格式构造
 * @author: ydcoding
 * @create: 2019-04-02 16:44
 **/
public class ResultVo <T>{

    private Integer code;

    private String msg;

    private T data;

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
