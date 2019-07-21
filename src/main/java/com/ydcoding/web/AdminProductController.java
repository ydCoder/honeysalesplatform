package com.ydcoding.web;

import com.ydcoding.entity.Product;
import com.ydcoding.entity.ProductCategory;
import com.ydcoding.service.ProductCategoryService;
import com.ydcoding.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-02 22:00
 **/
@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired

    private ProductCategoryService   productCategoryService;

    //商品列表
    @GetMapping("/product/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             Map<String, Object> map, HttpServletRequest httpServletRequest) {

        if( httpServletRequest.getSession().getAttribute("adminName")==null){
            return new ModelAndView("login/login");
        }
        PageRequest request = PageRequest.of(page - 1, size);
        Page<Product> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("admin/productlist", map);
    }

    //商品上下架操作

    @RequestMapping("product/upordown")
    @ResponseBody
    public Product upOrdown(@RequestParam("productId") String productId) {

        Integer Id=Integer.valueOf(productId);
        Product product=productService.findOne(Id).orElse(null);

        //商品状态等于0 表示在售，就调用下架方法
        if(product.getStatus()==0){
                Product result=productService.downSale(Id);
                return result;
        }
        else {
            Product result=productService.upSale(Id);
            return  result;
        }

    }

    //*新增/编辑商品
    @GetMapping("/product/addEdit")
    public ModelAndView index(@RequestParam(value = "productId", required = false) Integer productId,
                              Map<String, Object> map) {

        if (productId!=null) {
            Product product = productService.findOne(productId).orElse(null);
            map.put("product", product);
         } else {  //查询最后一个商品的id
                int  count=  productService.findSaleAll().size();
               //获取最后一个商品的id
                int  newProductId=productService.findSaleAll().get(count-1).getProductId()+1;
                Product product=new Product();
                product.setProductId(newProductId);
                map.put("product",product);
              }
        //查询所有的类目
        List<ProductCategory> categoryList =  productCategoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("admin/productaddEdit", map);

    }

    @PostMapping("/product/save")
    @ResponseBody
    public Product save(@RequestParam(value="productId",required = false) Integer productId,
                        @RequestParam("productName")String  productName,
                         @RequestParam("productPrice") BigDecimal productPrice,
                        @RequestParam("imgAddr") String imgAddr,
                        @RequestParam("productDesc") String productDesc,
                        @RequestParam("productStock") Integer productStock,
                        @RequestParam("categoryNo") Integer categoryNo,
                        @RequestParam("status") Integer status){

        Product product=new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setImgAddr(imgAddr);
        product.setProductDesc(productDesc);
        product.setCategoryNo(categoryNo);
        product.setProductStock(productStock);
        if(status==null){
            product.setStatus(0);
        }
       else{
           product.setStatus(status);
       }
        Product productResult=productService.save(product);
       return  productResult;
    }

}
