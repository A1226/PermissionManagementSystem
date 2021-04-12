package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

/**
 * 订单业务接口
 */
public interface OrdersService {
    //订单查询所有
    List<Orders> findAll(int page,int size);

    //根据ID查找
    Orders findById(String ordersId);
}
