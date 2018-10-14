package cn.itheima.dao;

import cn.itheima.domain.product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao {
    /**
     * 查询所有
     * @return
     */
     @Select("select * from product")
    List<product> findAll(int page,int size) throws Exception;
    /**
     * 添加
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus)values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    int save(product product) throws Exception;
    /**
     * 通过id查询
     */
    @Select("select * from product where id = #{id}")
    product findById(String id)throws Exception;
}
