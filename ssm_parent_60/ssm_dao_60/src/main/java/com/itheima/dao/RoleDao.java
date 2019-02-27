package com.itheima.dao;

import com.itheima.domain.Role;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import jdk.nashorn.internal.objects.annotations.Where;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface RoleDao {
    @Select("select * from sys_role")
    List<Role> findAllRole();
    @Insert("insert into sys_role values(com_sequence.nextval,#{roleName},#{roleDesc})")
    void saveRole(Role role);

    //根据用户id查询用户所有的角色列表
//    1. 子查询从用户角色中间表中 根据userid查询角色的子表
//    2.根据子表 查询角色集合
//    @Select("select * from sys_role where id in " +
//            "(select roleid from sys_user_role where userid = #{userId})")

@Select("select * from sys_user_role Join sys_role on userid =#{useId} and roleid = id")
    List<Role> findRolesByUserId(Integer useId);
}



















