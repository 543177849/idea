package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    //查询所有商品
    @RequestMapping("/findAllProduct")
    public String findAllProduct(Model model,
                                 @RequestParam(defaultValue = "1") Integer pageNum,
                                 @RequestParam(defaultValue = "3") Integer pageSize) {

        PageBean<Product> pb = productService.finAllProduct(pageNum,pageSize);
        model.addAttribute("pb", pb);
        return "product/productList";
    }

    //跳转添加商品的界面
    @RequestMapping("addProductUI")
    public String addProductUI() {
        return "product/productAdd";
    }

    //添加商品
    @RequestMapping("addProduct")
    public String addProduct(Product product) {
        productService.saveProduct(product);
        //保存列表 查询列表展示
        //需要用重定向，请求转发会携带数据，用户刷新重复提交
        return "redirect:/product/findAllProduct";
    }

    //修跳转改商品页面
    @RequestMapping("updateProductUI")
    public String updateProductUI(Integer id,Model model){

        Product p = productService.findProductById(id);
        model.addAttribute("product",p);
        return "product/productUpdate";
    }

    //接收产品对象传到数据库
    @RequestMapping("updateProduct")
    public String updateProduct(Product product){
        productService.updateProduct(product);
        //查询数据列表 重定向
        return "redirect:/product/findAllProduct";
    }

    @RequestMapping("deleteProduct")
    public String deleteProduct(Integer id){
        productService.deleteProduct(id);
        return "redirect:/product/findAllProduct";
    }

    @RequestMapping("deleteProductByIds")
    public String deleteProductByIds(Integer[] ids){
        System.out.println(ids);
        productService.deleteProductByIds(ids);

        return "redirect:/product/findAllProduct";

    }

}
