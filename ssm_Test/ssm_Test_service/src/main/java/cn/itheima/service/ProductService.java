package cn.itheima.service;

import cn.itheima.domain.product;

import java.util.List;

public interface ProductService {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    List<product> findAll(int page,int size) throws Exception;
    /**
     * 添加操作
     */
    int save(product product) throws Exception;
}
