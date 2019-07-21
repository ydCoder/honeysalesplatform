package com.ydcoding.dao;

import com.ydcoding.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @program: honeysalesplatform
 * @description   商品类目单元测试
 * @author: ydcoding
 * @create: 2019-03-29 16:50
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private  ProductCategoryDao productCategoryDao;

    @Test
    public void addTest(){
        //测试增加类目
        ProductCategory productCategory=new ProductCategory();

        productCategory.setCategoryName("美容养颜");
        productCategory.setCategoryNo(3);
        productCategoryDao.save(productCategory);
    }
    //  @Transactional  测试中的事务与在service中的不一致，测试中的就算通过数据库也不会留有测试数据，完全回滚到最初的状态
    // 而service中只是发生异常时数据才会回滚
    @Test
    @Transactional
    public void saveTest(){
        //测试增加类目 构造简化
        ProductCategory productCategory=new ProductCategory("美容养颜4",4);
        ProductCategory rs= productCategoryDao.save(productCategory);
        Assert.assertNotEquals(null,rs);//前者参数 不期待的对象，后:实际对象
        // 数据库中category_type设置唯一  类型重复添加会报错:Duplicate entry '18' for key 'type'
    }

    @Test
    public void deleteTest(){
        productCategoryDao.deleteById(4);
    }

    @Test
    public  void updateTest(){
        //测试更新类目   如果是一样的数据是不会更新的
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryId(3);
        productCategory.setCategoryName("强身健体10");
        productCategory.setCategoryNo(10);
        productCategoryDao.save(productCategory);
    }

    @Test
    public void findOneTest(){
        //测试查询类目
        //Optional<ProductCategory> Optional是Java8的新特性之一，它显示的告诉调用者该值可能是空值，需要做出判断，避免出现空指针异常。
       Optional<ProductCategory> productCategory= productCategoryDao.findById(1);
        System.out.println(productCategory.toString());
        // 若不存在，则输出Optional.empty  如果是之前返回ProductCategory类的类型参数接收，数据库无数据会报空指针异常
        //手动添加测试数据   输出：Optional[ProductCategory{categoryId=1, categoryName='最受欢迎', categoryNo=2}]
    }

    @Test
    public  void findByCategoryNoInTest(){
        List<Integer> list= Arrays.asList(2,3);
        List<ProductCategory> rs=productCategoryDao.findByCategoryNoIn(list);
        Assert.assertNotEquals(0,rs.size());


    }

}