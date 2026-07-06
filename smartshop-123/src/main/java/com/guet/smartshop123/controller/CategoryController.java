package com.guet.smartshop123.controller;

import com.guet.smartshop123.entity.Category;
import com.guet.smartshop123.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // 分类列表页面
    @GetMapping("/list")
    public String list(Model model) {
        List<Category> catList = categoryService.getList();
        model.addAttribute("catList", catList);
        return "category/list";
    }

    // 跳转新增分类页面
    @GetMapping("/toAdd")
    public String toAdd() {
        return "category/add";
    }

    // 提交新增分类
    @PostMapping("/add")
    public String add(Category category) {
        categoryService.add(category);
        return "redirect:/category/list";
    }

    // 跳转编辑页面，回显数据
    @GetMapping("/toEdit")
    public String toEdit(Integer id, Model model) {
        Category cat = categoryService.getById(id);
        model.addAttribute("cat", cat);
        return "category/edit";
    }

    // 提交编辑更新
    @PostMapping("/update")
    public String update(Category category) {
        categoryService.edit(category);
        return "redirect:/category/list";
    }

    // 带前置校验的分类删除接口（核心拦截逻辑）
    @GetMapping("/del")
    public String del(Integer id, Model model) {
        // 查询该分类下商品总数
        int productCount = categoryService.getProductCount(id);
        if (productCount > 0) {
            // 存在商品，不能删除，带回提示与列表数据
            model.addAttribute("msg", "该分类下存在商品，禁止删除！");
            model.addAttribute("catList", categoryService.getList());
            return "category/list";
        }
        // 无商品，正常执行删除
        categoryService.del(id);
        return "redirect:/category/list";
    }
}