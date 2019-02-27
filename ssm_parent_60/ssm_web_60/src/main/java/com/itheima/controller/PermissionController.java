package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/perimission")
public class PermissionController {
    @Autowired
        private PermissionService permissionService;

    @RequestMapping("/findAllPrimission")
    public String findAllPrimission(Model model){
        List<Permission> pList =permissionService.findAllPrimission();
        model.addAttribute("pList",pList);
        return "permission/permissionList";
    }
}
