package cn.itheima.service.impl;

import cn.itheima.dao.OrdersDao;
import cn.itheima.domain.Orders;
import cn.itheima.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImp implements OrdersService  {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //pageNum表示当前页码,pageSize表示每页显示的条数
        PageHelper.startPage(page, size);
        List<Orders> list = ordersDao.findAll(page, size);
        return list;
    }

    @Override
    public Orders findById(String id) throws Exception {
        Orders list = ordersDao.findById(id);
        return list;
    }
}
