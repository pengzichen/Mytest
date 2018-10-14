package cn.itheima.web;

import cn.itheima.domain.Role;
import cn.itheima.domain.UserInfo;
import cn.itheima.service.RoleService;
import cn.itheima.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserWeb {
    @Autowired
    private UserService userService;


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    @RolesAllowed("USER")
    public ModelAndView findAll() {
        List<UserInfo> userList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }


    /**
     * 用户详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        UserInfo userInfo = userService.findById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", userInfo);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }
    /**
     * 查询用户角色
     * @param id
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView  findUserByIdAndAllRole(String id){
        UserInfo user = userService.findById(id);
        List<Role> roleList = userService.findUserByIdAndAllRole(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleList",roleList );
        modelAndView.addObject("user",user );
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    /**
     * 给用户添加角色
     */
    @RequestMapping("/addRoleToUser.do")
    public String  addRoleToUser(String userId, String roleId){
         userService.addRoleToUser( userId,roleId);
        return "redirect:/user/findAll.do";
    }
}
