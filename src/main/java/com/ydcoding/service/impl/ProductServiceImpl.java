package com.ydcoding.service.impl;

import
        com.ydcoding.dao.ProductDao;
import com.ydcoding.dto.CartDto;
import com.ydcoding.emums.ProductStatusEnum;
import com.ydcoding.emums.ResultEnum;
import com.ydcoding.entity.Product;

import com.ydcoding.exception.SaleException;
import com.ydcoding.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-04-01 10:22
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    public Optional<Product> findOne(Integer productId) {
        return productDao.findById(productId);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDao.findAll(pageable);
    }


    @Override
    public List<Product> findSaleAll() {
        return productDao.findByStatus(ProductStatusEnum.SALE.getStatus());
    }

    @Override
    @Transactional
    public void addStock(List<CartDto> cartDtoList) {

        for (CartDto cartDto:cartDtoList) {
            Product product =  productDao.findById(cartDto.getProductId()).orElse(null);

            if(product==null){


                throw  new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
            }
             Integer  result=product.getProductStock()+cartDto.getProductQuantity();
            product.setProductStock(result);
            productDao.save(product);
        }

    }

    @Override
    @Transactional
    public void minusStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto:cartDtoList) {
           Product product =  productDao.findById(cartDto.getProductId()).orElse(null);


            System.out.println( "购物车的商品id:" + cartDto.getProductId());
//购物车的商品id:1

           if(product==null){

               System.out.println(product);
               throw  new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
           }


            System.out.println("商品保持量："+ product.getProductStock());
           Integer result= product.getProductStock()-cartDto.getProductQuantity();

            System.out.println("减购物车对应商品后存量为："+result);

           if(result<0){
               throw new SaleException(ResultEnum.PRODUCT_STOCK__NOT_ENOUGH);
           }
           product.setProductStock(result);

           productDao.save(product);
        }

 }




 //后台商品上下架

    @Override
    public Product upSale(Integer productId) {

       Product product= productDao.findById(productId).orElse(null);
        if (product == null) {
            throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStatusEnum() == ProductStatusEnum.SALE) {
            throw new SaleException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        product.setStatus(ProductStatusEnum.SALE.getStatus());
        return productDao.save(product);

    }

    @Override
    public Product downSale(Integer productId) {
        Product product= productDao.findById(productId).orElse(null);
        if (product == null) {
            throw new SaleException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (product.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SaleException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        product.setStatus(ProductStatusEnum.DOWN.getStatus());
        return productDao.save(product);
    }




}
