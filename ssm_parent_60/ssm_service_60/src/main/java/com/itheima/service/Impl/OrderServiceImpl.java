package com.itheima.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;


    @Override
    public PageInfo<Order> findAllOrder(Integer pageNum, Integer pageSize) {
        //开启分页插件的【静态方法】
        PageHelper.startPage(pageNum,pageSize);
        //紧接着下一行执行接口方法
        List<Order> olist = orderDao.findAllOrder();
        PageInfo<Order> pageInfo =new PageInfo<Order>(olist);
        return pageInfo;
    }
}
















