package com.itheima.dao;

import com.itheima.domain.Product;
import com.itheima.util.PageBean;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ProductDao {
    //sql语句未全部扫描 增加效率
    @Select("select * from (select rownum r,p.* from product p where rownum<=#{endIndex}) t " +
            "         where t.r>#{startIndex} ")
    //@Param绑定参数 不然系统不知道怎么传参
    List<Product> findAllProduct(@Param("startIndex") Integer startIndex, @Param("endIndex") Integer endIndex);

    @Insert("insert into product values(com_sequence.nextval,#{productNum}," +
            "#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void saveProduct(Product product);

    @Select("select * from product where id = #{id}")
    Product findProductById(Integer id);

    @Update("update product set productName=#{productName},cityName=#{cityName}," +
            " departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc}," +
            " productStatus=#{productStatus} where id = #{id} ")
    void updateProduct(Product product);

    @Delete("delete from product where id = #{productId}")
    void deleteProduct(Integer productId);


    //总计记录数用于分页查询
    @Select("select count(1) from product")
    Integer findTotalCount();
}
