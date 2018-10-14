package cn.itheima.service;

import cn.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 查询所有权限
     * @return
     */
    List<Permission> findAll()throws Exception;

    /**
     * 添加资源权限
     */
    void save(Permission permission);
}
