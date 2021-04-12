package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

/**
 * 业务层接口
 */
public interface ProductService {
    //查询所有
    List<Product> findAll();
    //保存产品
    void save(Product product);
}
