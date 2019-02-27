package com.itheima.service;

import com.itheima.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
//判断规则为：userService必须继承框架的接口UserDetailsService
//        目的是复写方法loadUserByUserName 通过用户名查询数据库的用户-->
public interface SysUserService extends UserDetailsService {
    List<SysUser> findAllUser();

    void saveUser(SysUser sysUser);

    SysUser findUserById(Integer userId);

    void managerUserRole(Integer userId, Integer[] ids);
}
