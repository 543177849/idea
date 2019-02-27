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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleServicel;


    //查询所有的角色 列表
    @RequestMapping("/findAllRole")
    public String findAllUser(Model model){
        List<Role> roleList = roleServicel.findAllRole();
        model.addAttribute("roleList",roleList);
        return "role/roleList";
    }

    @RequestMapping("addRoleUI")
    public String roleAddUI (){
        return "role/roleAdd";
    }

    @RequestMapping("saveRole")
    public String saveRole(Role role){
        roleServicel.saveRole(role);
        return "redirect:/role/findAllRole";
    }
}
