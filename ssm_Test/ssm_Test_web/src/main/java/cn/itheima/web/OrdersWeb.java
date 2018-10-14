package cn.itheima.web;

import cn.itheima.domain.Orders;
import cn.itheima.service.OrdersService;
import cn.itheima.service.impl.OrdersServiceImp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersWeb {
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Orders> list = ordersService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.setViewName("orders-list");
        modelAndView.addObject("pageInfo",pageInfo);
        return modelAndView;
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
       Orders list=ordersService.findById(id);
        modelAndView.addObject("orders",list);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }
}
