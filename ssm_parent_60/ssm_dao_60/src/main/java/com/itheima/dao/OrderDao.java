package com.itheima.dao;

import com.itheima.domain.Order;
import com.itheima.domain.Product;
import net.sf.jsqlparser.schema.Column;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import sun.swing.MenuItemLayoutHelper;

import java.util.List;

public interface OrderDao {
    //懒加载的模式查询所有订单
    @Select("select * from orders")
    //results表明结果是个集合
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one=@One(select = "com.itheima.dao.ProductDao.findProductById"))
    })
    public List<Order> findAllOrder();
}
















