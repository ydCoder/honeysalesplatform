package com.ydcoding.service.impl;

import com.ydcoding.dao.ProductCategoryDao;
import com.ydcoding.entity.ProductCategory;
import com.ydcoding.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-30 18:24
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    //引入dao

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }

    @Override
    public ProductCategory findByCategoryNo(Integer categoryNo) {
        return productCategoryDao.findBycategoryNo(categoryNo);
    }

    @Override
    public Optional<ProductCategory> findOne(Integer categoryId) {

        return productCategoryDao.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryNoIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryNoIn(categoryTypeList);
    }


}
