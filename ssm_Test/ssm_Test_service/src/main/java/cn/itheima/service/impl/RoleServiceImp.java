package cn.itheima.service.impl;

import cn.itheima.dao.RoleDao;
import cn.itheima.domain.Permission;
import cn.itheima.domain.Role;
import cn.itheima.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImp implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        List<Role> roleList =  roleDao.findAll();
        return roleList;
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void save(Role role) {
           roleDao.save(role);
    }

    /**
     * 根据id查询角色
     * @param id
     * @return
     */
    @Override
    public Role findById(String id) {
        Role role = roleDao.findById(id);
        return role;
    }

    /**
     * 查询角色没有的权限
     * @return
     */
    @Override
    public List<Permission> findRoleByIdAndAllPermission(String id) {
       List<Permission> list = roleDao.findRoleByIdAndAllPermission(id);
        return list;
    }

    @Override
    public void addPermissionToRole(String roleId, String permissionId) {
        roleDao.addPermissionToRole(roleId,permissionId);
    }
}
