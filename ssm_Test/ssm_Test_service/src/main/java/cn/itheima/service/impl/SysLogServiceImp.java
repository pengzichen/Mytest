package cn.itheima.service.impl;

import cn.itheima.dao.SysLogDao;
import cn.itheima.domain.SysLog;
import cn.itheima.service.SysLogService;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.net.SyslogAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImp implements SysLogService {

    @Autowired
   private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) {
        //pageNum表示当前页码,pageSize表示每页显示的条数
        PageHelper.startPage(page, size);
        List<SysLog> list = sysLogDao.findAll(page,size);
        return list;
    }
}
