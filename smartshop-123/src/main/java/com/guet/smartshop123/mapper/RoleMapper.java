package com.guet.smartshop123.mapper;
import com.guet.smartshop123.entity.Role;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface RoleMapper {
    @Select("SELECT r.* FROM t_role r LEFT JOIN t_user_role ur ON r.id=ur.role_id WHERE ur.user_id=#{userId}")
    List<Role> selectByUserId(Integer userId);
}