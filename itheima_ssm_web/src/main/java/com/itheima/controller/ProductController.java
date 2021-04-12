package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 产品
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查询所有
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView productService(){
        ModelAndView mv = new ModelAndView();
        List<Product> serviceAll = productService.findAll();
        mv.addObject("productList",serviceAll);
        mv.setViewName("product-list1");

        return mv;
    }
    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product){
         productService.save(product);
         return "redirect:findAll.do";
    }
}
