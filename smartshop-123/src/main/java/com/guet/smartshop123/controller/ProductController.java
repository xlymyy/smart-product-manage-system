package com.guet.smartshop123.controller;

import com.github.pagehelper.PageInfo;
import com.guet.smartshop123.ProductPageDTO;
import com.guet.smartshop123.entity.Product;
import com.guet.smartshop123.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // 商品分页列表+多条件搜索
    @GetMapping("/list")
    public String list(Model model, ProductPageDTO dto) {
        PageInfo<Product> pageData = productService.pageByDTO(dto);
        model.addAttribute("page", pageData);
        model.addAttribute("query", dto);
        return "product/list";
    }

    // 跳转新增商品页面
    @GetMapping("/toAdd")
    public String toAdd(){
        return "product/add";
    }

    // 提交新增商品
    @PostMapping("/add")
    public String add(Product product){
        product.setReleaseDate(new Date());
        productService.add(product);
        return "redirect:/product/list";
    }

    // 跳转编辑商品页面
    @GetMapping("/toEdit")
    public String toEdit(Integer id, Model model){
        if (id == null) {
            return "redirect:/product/list";
        }
        Product product = productService.getById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "product/edit";
    }

    // 提交商品更新
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/product/list";
    }

    // 删除商品接口
    @GetMapping("/del")
    public String del(Integer id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}