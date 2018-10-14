package cn.itheima.dao;

import cn.itheima.domain.Member;
import cn.itheima.domain.Orders;
import cn.itheima.domain.product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id = true,property ="id" ,column ="id" ),
            @Result(property ="orderNum" ,column ="orderNum"),
            @Result(property ="orderTime" ,column ="orderTime"),
            @Result(property ="orderTimeStr" ,column ="orderTimeStr"),
            @Result(property ="orderStatus" ,column ="orderStatus"),
            @Result(property ="orderStatusStr" ,column ="orderStatusStr"),
            @Result(property ="peopleCount" ,column ="peopleCount"),
            @Result(property ="payType" ,column ="payType"),
            @Result(property ="payTypeStr" ,column ="payTypeStr"),
            @Result(property ="orderDesc" ,column ="orderDesc"),
            @Result(property = "product",column ="productId",javaType = product.class,one = @One(select = "cn.itheima.dao.ProductDao.findById"))
    })
    List<Orders> findAll(int page,int size)throws Exception;
    @Select("select * from orders where id =#{id}")
    @Results({
            @Result(id = true,property ="id" ,column ="id" ),
            @Result(property ="orderNum" ,column ="orderNum"),
            @Result(property ="orderTime" ,column ="orderTime"),
            @Result(property ="orderStatus" ,column ="orderStatus"),
            @Result(property ="peopleCount" ,column ="peopleCount"),
            @Result(property ="payType" ,column ="payType"),
            @Result(property ="orderDesc" ,column ="orderDesc"),
            @Result(property = "product",column ="productId",javaType = product.class,one = @One(select = "cn.itheima.dao.ProductDao.findById")),
            @Result(property = "member",column ="memberId",javaType = Member.class,one = @One(select = "cn.itheima.dao.MemberDao.findById")),
            @Result(property = "travellers",column ="id",javaType = java.util.List.class,many = @Many(select = "cn.itheima.dao.TravellerDao.findById"))
    })
  Orders findById(String id) throws Exception;
}
