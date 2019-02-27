package com.itheima.dao;

import com.itheima.domain.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


public interface SysUserDao {
    @Select("select * from sys_user where username=#{username}")
    @Results({
            @Result(property = "id",column = "id"),
//            通过数据库中sysUser的id列查询
            @Result(property ="roles",column = "id",javaType = List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserId")
            )
    })
    SysUser findUserByName(String username);


    @Select("select * from sys_user")
    List<SysUser> findAllUser();
    @Insert("insert into sys_user values(com_sequence.nextval,#{username},#{email},#{password},#{phoneNum},#{status})")
    void sysUser(SysUser sysUser);


    @Select("select * from sys_user where id=#{userId}")
    @Results({
            @Result(property = "id",column = "id"),
//            通过数据库中sysUser的id列查询
            @Result(property ="roles",column = "id",javaType = List.class,
                many = @Many(select = "com.itheima.dao.RoleDao.findRolesByUserId")
            )
    })
    SysUser findUserById(Integer userId);


    @Insert("insert into sys_user_role values(#{userId},#{roleId})")
    void saveRole(@Param("userId")Integer userId, @Param("roleId")Integer id);


    @Delete("delete from sys_user_role where userid=#{userId}")
    void removeRoleFromUser(Integer userId);
}





















