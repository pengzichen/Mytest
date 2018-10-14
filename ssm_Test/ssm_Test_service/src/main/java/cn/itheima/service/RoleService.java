package cn.itheima.service;

import cn.itheima.domain.Permission;
import cn.itheima.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();

    /**
     * 添加角色
     * @param id
     */
    void save(Role role);
    /**
     * 根据id查询角色
     */
    Role findById(String id);

    /**
     * 根据id查询角色没有的权限
     * @return
     */
    List<Permission> findRoleByIdAndAllPermission(String id);

    /**
     * 给角色添加权限
     * @param roleId   角色id
     * @param permissionId   权限id
     */
    void addPermissionToRole(String roleId, String permissionId);
}
