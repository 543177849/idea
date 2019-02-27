package com.itheima.service;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;

public interface OrderService {
    PageInfo<Order> findAllOrder(Integer pageNum, Integer pageSize);
}
