package com.guet.smartshop123;

import lombok.Data;

@Data
public class ProductPageDTO {
    // 分页基础参数
    private Long current = 1L;
    private Long size = 5L;

    // 商品搜索条件
    private String name;
    private Double minPrice;
    private Double maxPrice;

    // 新增分类筛选ID
    private Long categoryId;
}