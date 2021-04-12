package com.itheima.service.Impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 业务层实现类
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    //商品查询所有
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    //产品添加
    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
