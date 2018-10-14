package cn.itheima.service.impl;

import cn.itheima.dao.RoleDao;
import cn.itheima.dao.UserDao;
import cn.itheima.domain.Role;
import cn.itheima.domain.UserInfo;
import cn.itheima.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo users = null;
        try {
            users = userDao.findByName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(users.getUsername(), "{noop}" + users.getPassword(), users.getStatus() == 1 ? true : false, true, true, true, getAuthority(users.getId()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(String id) {
        List<Role> RoleList = roleDao.findRoleByUserId(id);
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : RoleList) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> userList = userDao.findAll();
        return userList;
    }

    /**
     * 添加用户
     *
     * @param userInfo
     */
    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        UserInfo user = userDao.findById(id);
        return user;
    }

    /**
     * 给用户添加角色
     * @param
     */
    @Override
    public void addRoleToUser(String userId,String roleId) {
        userDao.addRoleToUser(userId,roleId);
    }

    /**
     * 查询用户可以添加的角色
     * @param id
     * @return
     */
    @Override
    public List<Role> findUserByIdAndAllRole(String id) {
       List<Role> list =  userDao.findUserByIdAndAllRole(id);
        return list;
    }
}
