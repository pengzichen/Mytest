package cn.itheima.web;

import cn.itheima.domain.SysLog;
import cn.itheima.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class sysLogWeb {
    @Autowired
     private SysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "5")Integer size){

        ModelAndView modelAndView = new ModelAndView();
        List<SysLog> list = sysLogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
