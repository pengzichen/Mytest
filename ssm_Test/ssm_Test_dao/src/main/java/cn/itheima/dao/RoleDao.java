package cn.itheima.dao;

import cn.itheima.domain.Permission;
import cn.itheima.domain.Role;
import com.sun.jmx.snmp.SnmpString;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;
@Repository
public interface RoleDao {
    /**
     * 根据用户id通过中间查询role集合 ,多对多查询
     * @return
     */
    @Select("select * from role where id in(select  roleid from users_role where userid =#{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id" ,javaType = List.class,many = @Many(select = "cn.itheima.dao.PermissionsDao.findByRoleId")),
            @Result(property = "users",column = "id",javaType = List.class,many = @Many(select = "cn.itheima.dao.UserDao.findByUserId")),
    })
    List<Role> findRoleByUserId(String id);

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     */
    @Insert("insert into role (rolename,roledesc)values(#{roleName},#{roleDesc})")
    void save(Role role);
    /**
     * 根据id查询角色
     */
    @Select("select * from role where id = #{id}")
    Role findById(String id);

    /**
     * 查询角色没有的权限
     * @return
     */
    @Select("select * from PERMISSION where id not  in (select permissionId from role_permission where roleid = #{id} )")
    List<Permission> findRoleByIdAndAllPermission(String id);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
