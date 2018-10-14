package cn.itheima.web;

import cn.itheima.domain.Permission;
import cn.itheima.domain.Role;
import cn.itheima.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleWeb {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     * @return
     */

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() {
         List<Role> roleList =  roleService.findAll();
         ModelAndView modelAndView = new ModelAndView();
         modelAndView.setViewName("role-list");
         modelAndView.addObject("roleList",roleList);
         return modelAndView;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RolesAllowed("USER")
    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    /**
     * 查询可以添加的权限
     * @param id
     * @return
     */
   @Secured("ROLE_USER")
   @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(String id){
       Role role = roleService.findById(id);
       List<Permission> list =  roleService.findRoleByIdAndAllPermission(id);
       ModelAndView modelAndView = new ModelAndView();
       modelAndView.addObject("permissionList",list);
       modelAndView.addObject("role",role);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
   }
    @PreAuthorize("authentication.principal.username == 'pengzichen'")
   @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String permissionId){
        roleService.addPermissionToRole(roleId,permissionId);
        return "redirect:/role/findAll.do";
   }
}
