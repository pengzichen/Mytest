package cn.itheima.dao;

import cn.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TravellerDao {
    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerid from order_traveller where  orderid = #{id}) ")
    List<Traveller> findById(String id) throws Exception;
}
