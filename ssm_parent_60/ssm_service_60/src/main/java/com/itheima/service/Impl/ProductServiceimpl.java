package com.itheima.service.Impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import com.itheima.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
//配置当前所有方法丢支持事务
@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
public class ProductServiceimpl implements ProductService{

    @Autowired
    private ProductDao productDao;
//    针对查询配置只读事务的支持 复写了类上面的信息
    @Transactional(readOnly = true)
    @Override
    public PageBean<Product> finAllProduct(Integer pageNum, Integer pageSize) {
        PageBean<Product> pageBean= new PageBean<>();
        pageBean.setPageNum(pageNum);
        pageBean.setPageSize(pageSize);

        Integer totalCount = productDao.findTotalCount();
        pageBean.setTotalCount(totalCount);

        Integer startIndex = (pageNum-1)*pageSize;
        Integer endIndex = pageNum*pageSize;
        List<Product>list = productDao.findAllProduct(startIndex,endIndex);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return productDao.findProductById(id);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer productId) {
        productDao.deleteProduct(productId);
    }

    @Override
    public void deleteProductByIds(Integer[] ids) {
        for (Integer id : ids) {
            productDao.deleteProduct(id);
        }
    }

}
