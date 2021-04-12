package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    //资源权限查询
    List<Permission> findAll();
    //资源权限添加
    void save(Permission permission);
}
