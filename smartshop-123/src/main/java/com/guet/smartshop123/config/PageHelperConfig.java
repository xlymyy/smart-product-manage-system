package com.guet.smartshop123.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Properties;

@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        // 开启合理化分页：页码<=1自动查第一页，页码>总页数自动查最后一页
        props.setProperty("reasonable", "true");
        // 支持多参数分页
        props.setProperty("supportMethodsArguments", "true");
        pageHelper.setProperties(props);
        return pageHelper;
    }
}