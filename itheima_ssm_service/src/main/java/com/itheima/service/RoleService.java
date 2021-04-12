package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

/**
 * 角色业务接口
 */
public interface RoleService {
    //查询所有
    List<Role> findAll();
    //保存
    void save(Role role);

    //根据ID查找角色
    Role findById(String roleId);

    //根据ID所有的权限
    List<Permission> findOtherPermission(String roleId);

    //给角色添加角色
    void addRoleToUser(String roleId, String[] permissions);
}
