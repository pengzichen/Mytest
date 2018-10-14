package cn.itheima.service;

import cn.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {
    /**
     * 查询所有
     */
    List<Orders> findAll(int page,int size) throws Exception;

    /**
     * 根据id查询订单所有信息
     * @param id
     * @return
     */
    Orders findById(String id) throws Exception;
}
