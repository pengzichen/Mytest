package cn.itheima.service;

import cn.itheima.domain.Role;
import cn.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo);

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    UserInfo findById(String id);

    /**
     * 给用户添加角色
     * @param ids
     */
    void addRoleToUser(String userId,String roleId);

    /**
     * 查询用户可以添加的角色
     * @param id
     * @return
     */
    List<Role> findUserByIdAndAllRole(String id);
}
