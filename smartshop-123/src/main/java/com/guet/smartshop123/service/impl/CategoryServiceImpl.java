package com.guet.smartshop123.service.impl;
import com.guet.smartshop123.entity.Category;
import com.guet.smartshop123.mapper.CategoryMapper;
import com.guet.smartshop123.mapper.ProductMapper;
import com.guet.smartshop123.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    // 新增注入ProductMapper
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private static final String CACHE_KEY = "category:all";

    @Override
    public List<Category> getList() {
        List<Category> cache = (List<Category>) redisTemplate.opsForValue().get(CACHE_KEY);
        if(cache != null) return cache;
        List<Category> list = categoryMapper.selectAll();
        redisTemplate.opsForValue().set(CACHE_KEY,list,10, TimeUnit.MINUTES);
        return list;
    }

    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    public void edit(Category category) {
        categoryMapper.update(category);
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    public void del(Integer id) {
        categoryMapper.deleteById(id);
        redisTemplate.delete(CACHE_KEY);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }

    // 实现接口新增统计方法
    @Override
    public int getProductCount(Integer catId) {
        return productMapper.countByCatId(catId);
    }
}