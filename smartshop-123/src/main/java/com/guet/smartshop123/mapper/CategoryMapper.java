package com.guet.smartshop123.mapper;
import com.guet.smartshop123.entity.Category;
import org.apache.ibatis.annotations.*;
import java.util.List;
public interface CategoryMapper {
    @Select("SELECT * FROM category")
    List<Category> selectAll();

    @Insert("INSERT INTO category(name,descp) VALUES(#{name},#{descp})")
    void insert(Category category);

    @Update("UPDATE category SET name=#{name},descp=#{descp} WHERE id=#{id}")
    void update(Category category);

    @Delete("DELETE FROM category WHERE id=#{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM category WHERE id=#{id}")
    Category selectById(Integer id);
}