package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询所有用户
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll();
        mv.addObject("userList",userInfos);
        mv.setViewName("user-list");
        return mv;
    }
    //添加用户
    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username == 'tom'")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
    //用户详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfos = userService.findById(id);
        mv.addObject("user",userInfos);
        mv.setViewName("user-show");
        return mv;
    }

    //查询用户及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> userRole = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",userRole);
        mv.setViewName("user-role-add");
        return mv;
    }

    //添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roles){
        userService.addRoleToUser(userId,roles);
        return "redirect:findAll.do";
    }
}
