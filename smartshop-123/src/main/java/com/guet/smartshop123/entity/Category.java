package com.guet.smartshop123.entity;
import java.io.Serializable;

// 实现序列化接口，支持Redis缓存
public class Category implements Serializable {
    private Integer id;
    private String name;
    private String descp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}