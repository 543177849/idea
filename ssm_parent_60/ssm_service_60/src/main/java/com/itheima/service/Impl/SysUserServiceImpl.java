package com.itheima.service.Impl;

import com.itheima.dao.SysUserDao;
import com.itheima.domain.Role;
import com.itheima.domain.SysUser;
import com.itheima.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("userService")
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;
    //初始化BCryptPasswordEncoder 只生成一次
    @Autowired
    private BCryptPasswordEncoder pwdEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 得到数据库的用户
        SysUser sysUser = sysUserDao.findUserByName(s);
        //得到框架的User对象用于验证返回（注意别导错包） 构造方法有三个参数：用户名 密码 用户的权限集合
        //测试创建权限集合赋予权限
        List<GrantedAuthority> authoritylist = new ArrayList<GrantedAuthority>();
//        获取用户的角色集合
        //SimpleGrantedAuthority为接口GrantedAuthority的实现类
        List<Role> userRoles = sysUser.getRoles();
        if(userRoles!=null&&userRoles.size()>0){
            for (Role userRole : userRoles) {
                authoritylist.add(new SimpleGrantedAuthority(userRole.getRoleName()));
            }
        }

        // 注意下面的【User】是权限框架中userdetails接口内的实现对象！！ 用于验证
        User user = new User(sysUser.getUsername(),sysUser.getPassword(),authoritylist);
        return user;
    }

    @Override
    public List<SysUser> findAllUser() {
        List<SysUser> userList = sysUserDao.findAllUser();
        return userList;
    }

    @Override
    public void saveUser(SysUser sysUser) {
        //获取user对象中的原始密码
        String password = sysUser.getPassword();
        //密文加密
        sysUser.setPassword(pwdEncoder.encode(password));
        sysUserDao.sysUser(sysUser);
    }


    @Override
    public SysUser findUserById(Integer userId) {
        return sysUserDao.findUserById(userId);
    }

    @Override
    public void managerUserRole(Integer userId, Integer[] ids) {
        //清空以前的角色信息
        sysUserDao.removeRoleFromUser(userId);
        //用户没有角色
        if(ids!=null && ids.length>0){
            for (Integer id : ids) {
                sysUserDao.saveRole(userId,id);
            }
        }
    }

}












