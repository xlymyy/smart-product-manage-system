package com.guet.smartshop123.mapper;

import com.guet.smartshop123.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ProductMapper {
    // 只保留唯一getAll方法，删除上方多余孤立@Select
    @Select("SELECT p.*,c.name categoryName FROM product p LEFT JOIN category c ON p.cat_id=c.id")
    List<Product> getAll();

    @Select("<script>" +
            "SELECT p.*,c.name categoryName FROM product p LEFT JOIN category c ON p.cat_id=c.id " +
            "<where>" +
            "<if test='catId != null'>p.cat_id = #{catId}</if>" +
            "<if test=\"name != null and name != ''\">p.name LIKE concat('%',#{name},'%')</if>" +
            "<if test='minPrice != null'>p.price &gt;= #{minPrice}</if>" +
            "<if test='maxPrice != null'>p.price &lt;= #{maxPrice}</if>" +
            "</where>" +
            "</script>")
    List<Product> search(
            @Param("catId") Integer catId,
            @Param("name") String name,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );

    @Select("SELECT * FROM product WHERE id=#{id}")
    Product getById(Integer id);

    @Insert("INSERT INTO product(name,photo_url,price,descp,release_date,cat_id) VALUES(#{name},#{photoUrl},#{price},#{descp},#{releaseDate},#{catId})")
    void insert(Product product);

    @Update("UPDATE product SET name=#{name},photo_url=#{photoUrl},price=#{price},descp=#{descp},release_date=#{releaseDate},cat_id=#{catId} WHERE id=#{id}")
    void update(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    void delete(Integer id);

    // 统计指定分类下商品数量
    @Select("SELECT COUNT(*) FROM product WHERE cat_id = #{catId}")
    int countByCatId(@Param("catId") Integer catId);
}