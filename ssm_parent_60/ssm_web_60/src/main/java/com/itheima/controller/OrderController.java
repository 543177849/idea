package com.itheima.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
@Autowired
private OrderService orderService;

    @RequestMapping("/findAllOrder")
    public String findAllOrder(Model model,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "1") Integer pageSize){
        PageInfo<Order> pageInfo = orderService.findAllOrder(pageNum,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "order/orderList";
    }
}















