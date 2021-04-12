package com.itheima.service.Impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    //查询所有的角色
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    //添加角色
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    //根据ID查找角色
    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    //根据ID所有的权限
    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return roleDao.findOtherPermission(roleId);
    }

    //角色添加角色
    @Override
    public void addRoleToUser(String roleId, String[] permissions) {
        for (String permission : permissions) {
            roleDao.addRoleToUser(roleId,permission);
        }
    }
}
