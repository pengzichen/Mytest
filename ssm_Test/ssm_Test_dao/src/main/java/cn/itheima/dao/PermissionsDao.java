package cn.itheima.dao;

import cn.itheima.domain.Permission;
import net.sf.jsqlparser.statement.select.FromItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;
import java.security.Permissions;
import java.util.List;
@Repository
public interface PermissionsDao {

    @Select("Select * from permission where id in(select permissionId from role_permission where roleId = #{id})")
    List<Permission> findByRoleId(String id);

    /**
     * 查询所有权限
     * @return
     */
    @Select("select * from permission")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property ="permissionName" ,column = "permissionName"),
            @Result(property ="url" ,column = "url"),
    })
    List<Permission> findAll() throws Exception;

    /**
     * 添加资源路径
     */
    @Insert("insert into permission (PERMISSIONNAME,url)values (#{permissionName},#{url}) ")
    void save(Permission permission);
}
