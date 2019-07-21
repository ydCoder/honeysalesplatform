package com.ydcoding.web;
import com.ydcoding.entity.ProductCategory;

import com.ydcoding.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-03-09 19:51
 **/
@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    //类目列表
   @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map, HttpServletRequest httpServletRequest) {
       if( httpServletRequest.getSession().getAttribute("adminName")==null){
           return new ModelAndView("login/login");
       }
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("admin/categorylist", map);
    }
//新增/编辑类目
    @RequestMapping("/addEdit")
    public ModelAndView update(@RequestParam(value = "categoryId", required = false)
                                           Integer categoryId, Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId).orElse(null);
            map.put("category", category);
            return new ModelAndView("admin/categoryAddEdit");
        }
        return new ModelAndView("admin/categoryAddEdit");
    }

    //查询是否已存在类目
    @RequestMapping("/exist")
    @ResponseBody
    public ProductCategory exist(@RequestParam Integer categoryNo){
       if(categoryService.findByCategoryNo(categoryNo)!=null){
           Integer categoryId=categoryService.findByCategoryNo(categoryNo).getCategoryId();
           return new ProductCategory(categoryId);

       }
       //构造一个0值Id
        Integer categoryId=0;
        return  new ProductCategory(categoryId);
    }

//更新保存类目
    @RequestMapping("/save")
    @ResponseBody
    public ProductCategory save(ProductCategory productCategory){
        if(productCategory.getCategoryId()==null){
            Integer lastCategoryId=categoryService.findAll().size()-1;
            Integer newCategoryId=categoryService.findAll().get(lastCategoryId).getCategoryId()+1;
            ProductCategory  addCategory=new ProductCategory();
            addCategory.setCategoryId(newCategoryId);
            addCategory.setCategoryNo(productCategory.getCategoryNo());
            addCategory.setCategoryName(productCategory.getCategoryName());
         }
            return   categoryService.save(productCategory);
   }
}