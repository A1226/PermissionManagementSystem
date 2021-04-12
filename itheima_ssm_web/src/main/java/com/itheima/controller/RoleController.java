package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有的角色
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    //添加角色
    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    //根据ID查找角色以及所有的权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) {
        ModelAndView mv = new ModelAndView();
        Role roleAndPermission = roleService.findById(roleId);
        List<Permission> permissions = roleService.findOtherPermission(roleId);
        mv.addObject("roles", roleAndPermission);
        mv.addObject("permissionsList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    //给角色添加权限
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[] permissions){
        roleService.addRoleToUser(roleId,permissions);

        return "redirect:findAll.do";
    }
}
