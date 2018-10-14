package cn.itheima.web;

import cn.itheima.domain.product;
import cn.itheima.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.security.krb5.internal.PAData;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductWeb {
    @Autowired
    private ProductService productService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page,@RequestParam(name = "size",required = true,defaultValue = "4") Integer  size) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<product> list = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("list");
        System.out.println(list);
       return modelAndView;
    }
    @RequestMapping("/save.do")
    public String save(product product) throws Exception {
        int i = productService.save(product);
        if(i!=0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        return "redirect:/product/findAll.do";
    }
}
