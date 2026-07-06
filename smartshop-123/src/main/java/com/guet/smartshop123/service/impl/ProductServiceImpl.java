package com.guet.smartshop123.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guet.smartshop123.ProductPageDTO;
import com.guet.smartshop123.entity.Product;
import com.guet.smartshop123.mapper.ProductMapper;
import com.guet.smartshop123.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageInfo<Product> pageQuery(Integer pageNum, Integer pageSize, Integer catId, String name, Double min, Double max) {
        PageHelper.startPage(pageNum, pageSize);
        // 传参顺序对应mapper四个参数：catId、name、minPrice、maxPrice
        List<Product> list = productMapper.search(catId, name, min, max);
        return new PageInfo<>(list);
    }

    @Cacheable(value = "product", key = "#id")
    @Override
    public Product getById(Integer id) {
        return productMapper.getById(id);
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @CachePut(value = "product", key = "#product.id")
    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @CacheEvict(value = "product", key = "#id")
    @Override
    public void delete(Integer id) {
        productMapper.delete(id);
    }

    @Override
    public PageInfo<Product> pageByDTO(ProductPageDTO dto) {
        // 分页参数判空，给默认值
        int pageNum = dto.getCurrent() == null ? 1 : dto.getCurrent().intValue();
        int pageSize = dto.getSize() == null ? 5 : dto.getSize().intValue();

        // 分类ID判空，避免null调用intValue()空指针
        Integer catId = null;
        if (dto.getCategoryId() != null) {
            catId = dto.getCategoryId().intValue();
        }

        String name = dto.getName();
        Double minPrice = dto.getMinPrice();
        Double maxPrice = dto.getMaxPrice();

        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productMapper.search(catId, name, minPrice, maxPrice);
        return new PageInfo<>(list);
    }
}