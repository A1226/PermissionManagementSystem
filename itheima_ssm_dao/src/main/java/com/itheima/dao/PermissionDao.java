package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    /**
     * 根据Id查找相关的权限信息
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> permissionByRoleId(String id);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll();

    //添加
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);
}
