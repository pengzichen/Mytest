package cn.itheima.web;

import cn.itheima.domain.Permission;
import cn.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionWeb {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        List<Permission> list = permissionService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("permissionList",list);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }
    @RequestMapping("save.do")
    public String save(Permission permission){
        permissionService.save(permission);
       return "redirect:/permission/findAll.do";

    }
}
