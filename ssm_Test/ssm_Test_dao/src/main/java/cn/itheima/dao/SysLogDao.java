package cn.itheima.dao;

import cn.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogDao {
    /**
     * 添加日志
     * @param sysLog
     */
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);
    /**
     * 查询所有日志
     */
    @Select("select * from syslog")
    List<SysLog> findAll(Integer page,Integer size);
}
