package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {
    /**
     * 根据用户ID查询出所有的对应角色
     * @param id
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.dao.PermissionDao.permissionByRoleId")),
    })
    List<Role> findRoleByUserId(String userId);

    /**
     * 查询所有
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    //保存角色添加
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    //根据ID查找角色
    @Select("select * from role where id = #{roleId}")
    Role findById(String roleId);

    //根据ID所有的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId} )")
    List<Permission> findOtherPermission(String roleId);

    //角色添加权限
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permission})")
    void addRoleToUser(@Param("roleId") String roleId,@Param("permission") String permission);
}
