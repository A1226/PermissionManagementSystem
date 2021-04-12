package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品信息持久层接口
 */
public interface ProductDao {

    //根据ID查询产品
    @Select("select * from product where id = #{id}")
    Product findById(String id);

    //查询产品所有信息
    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
