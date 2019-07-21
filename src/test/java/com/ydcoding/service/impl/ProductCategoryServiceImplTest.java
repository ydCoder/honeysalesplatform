package com.ydcoding.service.impl;

import com.ydcoding.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-30 18:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;


    @Test
    public void save() {
        ProductCategory productCategory=new ProductCategory("老年专享",8);
        ProductCategory rs= productCategoryService.save(productCategory);
        Assert.assertNotNull(rs);

    }

    @Test
    public void findOne() {
        Optional<ProductCategory> productCategory=productCategoryService.findOne(1);
        //productCategory.get()    findOne(11); 如果在这个Optional中包含这个值，返回值，否则抛出异常：NoSuchElementException
        Assert.assertEquals(new Integer(1),productCategory.get().getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategoryList=productCategoryService.findAll();
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void findByCategoryNoIn() {
        List<ProductCategory>  productCategoryList=productCategoryService.findByCategoryNoIn(Arrays.asList(1,2,3,4,5));
        Assert.assertNotEquals(0,productCategoryList.size());
    }
}