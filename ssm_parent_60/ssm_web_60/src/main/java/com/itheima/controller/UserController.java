package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.RoleService;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RoleService roleService;


    //查询所有的用户列表
    @RequestMapping("/findAllUser")
    public String findAllUser(Model model){
        List<SysUser> userList = sysUserService.findAllUser();
        model.addAttribute("userList",userList);
        return "user/userList";
    }
    //请求跳转增加用户页面
    @RequestMapping("/addSysUserUI")
    public String addSysUser(){
        return "user/userAdd";
    };

    @RequestMapping("/saveUser")
    public String saveUser(SysUser sysUser){
        sysUserService.saveUser(sysUser);
        return "redirect:/user/findAllUser";
    }

//    跳转用户角色管理页面
    @RequestMapping("/managerUserRoleUI")
    public String managerUserRoleUI(Integer userId,Model model){
        //得到user对象
        SysUser sysUser = sysUserService.findUserById(userId);
        //用户角色信息
        List<Role> userRoles = sysUser.getRoles();
        //数据库的所有角色
        List<Role> roles = roleService.findAllRole();

        model.addAttribute("user",sysUser);
        // 遍历所有用户的角色 组装成一个字符串  返回页面用于判断
        if(null!=userRoles){
            StringBuilder sb = new StringBuilder();
            for (Role userRole : userRoles) {
               sb.append(userRole.getRoleName()+",");
            }
            model.addAttribute("roleStr",sb.toString());
        }


        model.addAttribute("roles",roles);
        System.out.println("用户为==========================================================="+sysUser);
        System.out.println("用户的角色个数为==========================================================="+userRoles.size()+userRoles);
        System.out.println("所有角色为==========================================================="+roles);
        return "user/managerUserRole";

    }

    //维护中间表
    @RequestMapping("managerUserRole")
    public String managerUserRole(Integer userId,Integer[] ids){
        sysUserService.managerUserRole(userId,ids);
        return "redirect:/user/findAllUser";
    }

}











