package com.ydcoding.dao;

import com.ydcoding.entity.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-31 19:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoTest {

    @Autowired
    private  ProductDao productDao;

    @Test
    public   void saveTest(){
        //测试增加商品 修改也调用此方法
        Product product=new Product(4,"洋槐蜜",
                                    new BigDecimal(66.6),
                                    "http://www.suse.jsj.cn/gqhoney",
                                    "美容养颜2",66,
                                    16,0);

                 Product  rs=productDao.save(product);
                 Assert.assertNotNull(rs);



    }


    @Test
    public void findByStatus() {
        List<Product> productList=productDao.findByStatus(0);
        System.out.println(productList.get(0).toString());
        /*
        Product{productId=1, productName='洋槐蜜', productPrice=66.60,
         imgAddr='http://www.suse.jsj.cn', productDesc='美容养颜',
         productAmount=66, categoryNo=6, status=0}

         */
        Assert.assertNotEquals(0,productList.size());

    }
}