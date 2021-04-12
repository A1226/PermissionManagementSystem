package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    //用户查询所有
    List<UserInfo> findAll();

    //用户表添加用户
    void save(UserInfo userInfo);

    //根据ID查找用户
    UserInfo findById(String id);

    //根据ID查询能添加的角色
    List<Role> findOtherRoles(String userId);

    //添加角色
    void addRoleToUser(String userId, String[] roles);
}
