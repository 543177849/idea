package com.itheima.service;

import com.itheima.domain.Product;
import com.itheima.util.PageBean;

import java.util.List;

public interface ProductService {

    PageBean<Product> finAllProduct(Integer pageNum, Integer pageSize);

    void saveProduct(Product product);

    Product findProductById(Integer id);

    void updateProduct(Product product);


    void deleteProduct(Integer productId);

    void deleteProductByIds(Integer[] ids);
}
