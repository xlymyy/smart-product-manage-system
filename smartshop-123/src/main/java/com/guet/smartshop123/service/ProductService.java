package com.guet.smartshop123.service;
import com.guet.smartshop123.ProductPageDTO;
import com.github.pagehelper.PageInfo;
import com.guet.smartshop123.entity.Product;
public interface ProductService {
    PageInfo<Product> pageQuery(Integer pageNum, Integer pageSize, Integer catId, String name, Double min, Double max);
    PageInfo<Product> pageByDTO(ProductPageDTO dto);
    Product getById(Integer id);
    void add(Product product);
    void update(Product product);
    void delete(Integer id);
}