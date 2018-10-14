package cn.itheima.dao;

import cn.itheima.domain.Role;
import cn.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 用户登录权限查询
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property ="id",column = "id"),
            @Result( property ="username",column = "username"),
            @Result( property ="email",column = "email"),
            @Result( property ="password",column = "password"),
            @Result( property ="phoneNum",column = "phoneNum"),
            @Result( property ="status",column = "status"),
            @Result( property ="roles",column = "id",javaType = List.class,many = @Many(select = "cn.itheima.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findByName(String username) throws Exception;

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();
    @Insert("insert into users(email,username,password,phoneNum,status)values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * 用户详情查询
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles" ,column = "id",javaType = List.class,many = @Many(select = "cn.itheima.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findById(String id);

    /**
     * 根据角色表查询用户
     * @param id
     * @return
     */
    @Select("select * from users where id  in (select userId from users_role where roleId = #{id} )")
    List<UserInfo> findByUserId(String id);

    /**
     * gei用户添加角色
     * @param ids
     */
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId")String userId,@Param("roleId")String roleId);

    /**
     * 查询用户可以添加的角色
     * @param id
     * @return
     */
    @Select("select *  from role where id not in (select roleid from users_role where  userid = #{id} ) ")
    List<Role> findUserByIdAndAllRole(String id);
}
