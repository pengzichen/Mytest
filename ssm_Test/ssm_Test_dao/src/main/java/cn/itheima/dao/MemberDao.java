package cn.itheima.dao;

import cn.itheima.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {
    /**
     * 通过id查询
     */
    @Select("select * from member where id = #{id}")
    Member findById(String id) throws Exception;
}
