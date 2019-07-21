package com.ydcoding.service.impl;

import com.ydcoding.emums.ProductStatusEnum;
import com.ydcoding.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-01 11:15
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

//    @Test
//    public void save() {
//
//        Product product=new Product(5,"洋槐蜜3",
//                new BigDecimal(66.6),
//                "http://www.suse.jsj.cn/gqhoney3",
//                "好喝好喝",68,
//                2, ProductStatusEnum.SALE.getStatus());
//
//        Product  result=productService.save(product);
//        Assert.assertNotNull(result);
//
//
//    }

    @Test
    public void findOne() {


        Optional<Product> product = productService.findOne(1);
        //注意查看控制台输出不同的对象的toString 方法
        System.out.println(product.toString());
        /*Optional[Product{productId=1,
         productName='枸杞蜜', productPrice=66.60,
         imgAddr='http://www.suse.jsj.cn/gqhoney',
         productDesc='养生专享', productAmount=66,
         categoryNo=6, status=0}]*/

        System.out.println(product.get().toString());
        /*Product{productId=1, productName='枸杞蜜',
        productPrice=66.60, imgAddr='http://www.suse.jsj.cn/gqhoney',
        productDesc='养生专享', productAmount=66, categoryNo=6, status=0}*/



        Assert.assertNotNull(product);
    }

    @Test
    public void findAll() {
        PageRequest request=PageRequest.of(0,2);
        Page<Product> productPage=productService.findAll(request);
        System.out.println("result ："+productPage.getTotalElements()); //result ：5
    }

    @Test
    public void findSaleAll() {
        List<Product> productList=productService.findSaleAll();
        Assert.assertNotEquals(0,productList.size());
    }



    @Test
    public void upSale() {
        Product result = productService.upSale(1);
        Assert.assertEquals(ProductStatusEnum.SALE, result.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        Product result = productService.downSale(1);
        Assert.assertEquals(ProductStatusEnum.DOWN, result.getProductStatusEnum());
    }


}