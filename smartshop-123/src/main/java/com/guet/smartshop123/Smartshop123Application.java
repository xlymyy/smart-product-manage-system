package com.guet.smartshop123;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
// 扫描所有Mapper接口，不能注释！
@MapperScan("com.guet.smartshop123.mapper")
// 开启Redis缓存
@EnableCaching
public class Smartshop123Application {
    public static void main(String[] args) {
        SpringApplication.run(Smartshop123Application.class, args);
    }
}