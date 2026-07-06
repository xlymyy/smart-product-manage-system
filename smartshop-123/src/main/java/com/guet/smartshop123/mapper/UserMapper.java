package com.guet.smartshop123.mapper;
import com.guet.smartshop123.entity.User;
import org.apache.ibatis.annotations.Select;
public interface UserMapper {
    @Select("SELECT * FROM t_user WHERE username=#{username}")
    User selectByUsername(String username);
}