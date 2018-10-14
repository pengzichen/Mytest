package cn.itheima.service.impl;

import cn.itheima.dao.ProductDao;
import cn.itheima.domain.product;
import cn.itheima.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Override
    public List<product> findAll( int page,int size) throws Exception {
        //pageNum表示当前页码,pageSize表示每页显示的条数
        PageHelper.startPage(page,size);
        List<product> list = productDao.findAll(page,size);
        return list;
    }

    @Override
    public int save(product product) throws Exception {
        int i = productDao.save(product);
        return i;
    }
}
