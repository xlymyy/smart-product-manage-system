package com.guet.smartshop123.service;

import com.guet.smartshop123.entity.Category;
import java.util.List;

public interface CategoryService {
    // 查询全部分类列表
    List<Category> getList();
    // 新增分类
    void add(Category category);
    // 修改分类
    void edit(Category category);
    // 删除分类
    void del(Integer id);
    // 根据id查询单个分类
    Category getById(Integer id);
    // 查询该分类下商品数量
    int getProductCount(Integer catId);
}