package com.ydcoding.web;

import com.ydcoding.util.JsonUtil;
import com.ydcoding.util.QiniuUtil;
import com.ydcoding.util.ResultVoUtil;
import com.ydcoding.view.ImgUrlVo;
import com.ydcoding.view.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-14 19:39
 **/
@Controller
@RequestMapping("/admin/product")
public class UploadController {
    @Autowired
    private  QiniuUtil qiniuUtil;
    @Autowired  ImgUrlVo imgUrlVo;
    private static final String PRODUCT_IMG = "product";


    @RequestMapping("/img/upload")
    @ResponseBody
    public ResultVo<ImgUrlVo> uploadImg(@RequestParam("file") MultipartFile file,
                                        HttpServletRequest req,
                                        @RequestParam(value="productId",
                                                required = false) Integer productId) throws IOException {
        //指定名字a.jpg
        //String imgUrl=qiniuUtil.uploadImg((FileInputStream)file.getInputStream(), "a.jpg");
        //定义图片上传的名字
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        String imgName=PRODUCT_IMG+productId+df.format(new Date())+".jpg";
        String imgUrl=qiniuUtil.uploadImg((FileInputStream)file.getInputStream(), imgName);
        imgUrlVo.setSrc(imgUrl);
        System.out.println("图片上传成功后返回值："+JsonUtil.toJson(ResultVoUtil.success(imgUrlVo)));
        return ResultVoUtil.success(imgUrlVo);
    }
}
