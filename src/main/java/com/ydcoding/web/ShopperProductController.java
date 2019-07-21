package com.ydcoding.web;

import com.ydcoding.entity.Product;
import com.ydcoding.entity.ProductCategory;
import com.ydcoding.service.ProductCategoryService;
import com.ydcoding.service.ProductService;
import com.ydcoding.util.ResultVoUtil;
import com.ydcoding.view.ProductInfoVo;
import com.ydcoding.view.ProductVo;
import com.ydcoding.view.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @program: honeysalesplatform
 * @description 卖家端商品
 * @author: ydcoding
 * @create: 2019-04-02 16:33
 **/
@RestController
@RequestMapping("/shopper/product")
public class ShopperProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVo list() {
        //1:查询所有的在售商品
        List<Product> productList = productService.findSaleAll();
        //2.并用lambda表达式商品中的所有编号提取处理啊存放到list集合里
        List<Integer> categoryNoList = productList.stream().
                map(e -> e.getCategoryNo()).
                collect(Collectors.toList());
        //查询所有商品类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryNoIn(categoryNoList);
        //3.把数据库查询输的数据拼装构造成前端展示所需要对应格式的逻辑

        //创建集合存储前台需要返回的商品数据
        List<ProductVo> productVoList = new ArrayList<>();
        //给每一个类目都预先构造出 一个返回对象
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryNo(productCategory.getCategoryNo());
            //productInfo是product的一部分信息，这里通过判断的类目编号将类目归类
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            //将所有商品遍历出来找对应的类目信息
            for (Product product : productList) {
                if (product.getCategoryNo().equals(productCategory.getCategoryNo())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(product, productInfoVo);
                    //初次运行前端相关对应值为空，是因为忘记把productInfoVo视图对象add到集合里去了
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }
        //使用工具类封装通用方法
        return ResultVoUtil.success(productVoList);
    }
}
