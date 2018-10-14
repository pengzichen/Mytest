package cn.itheima.service;

import cn.itheima.domain.SysLog;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

public interface SysLogService {
    /**
     * 添加日志
     * @param sysLog
     */
    void save(SysLog sysLog);

    /**
     * 查询所有日志
     * @return
     */
    List<SysLog> findAll(Integer page, Integer size);
}
