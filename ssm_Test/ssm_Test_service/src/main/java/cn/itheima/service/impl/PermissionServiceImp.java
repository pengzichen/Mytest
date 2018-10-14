package cn.itheima.service.impl;

import cn.itheima.dao.PermissionsDao;
import cn.itheima.domain.Permission;
import cn.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionsDao permissionsDao;
    @Override
    public List<Permission> findAll() throws Exception {
        List<Permission> list = permissionsDao.findAll();
        return list;
    }

    @Override
    public void save(Permission permission) {
        permissionsDao.save(permission);
    }
}
