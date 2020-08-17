package com.dxh.controller;

import com.dxh.entity.Product;
import com.dxh.myexception.MyException;
import com.dxh.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/to_list")
    public ModelAndView toList() throws MyException {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> products = productService.findAll();

        modelAndView.addObject("productList",products);

        modelAndView.setViewName("productlist");
        return modelAndView;
    }

    @RequestMapping("/find")
    @ResponseBody
    public Map<String,Object> getAll(HttpServletRequest request) throws Exception {
        String pageNow = request.getParameter("page");
        String pageSize = request.getParameter("limit");

        PageHelper.startPage(Integer.parseInt(pageNow),Integer.parseInt(pageSize));

        List<Product> products = productService.findAll();

        PageInfo pageInfo = new PageInfo(products);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",pageInfo.getTotal());
        map.put("data",products);

        /*String json = objectMapper.writeValueAsString(map);

        response.setContentType("text/html;charset=utf-8");

        response.getWriter().write(json);*/

        return map;
    }

    @RequestMapping("/itemEdit/{id}")
    public ModelAndView getById(@PathVariable(value = "id") String id){
        ModelAndView modelAndView = new ModelAndView();


        Product product = productService.findById(id);

        modelAndView.addObject("item",product);

        modelAndView.setViewName("product_item");

        return modelAndView;
    }

    @RequestMapping("/updateitem.action")
    public  void updateitem(Product product, MultipartFile pictureFile, HttpServletResponse response) throws Exception {
        if(!pictureFile.isEmpty()){
            String originalFilename = pictureFile.getOriginalFilename();

            String   FileName = UUID.randomUUID().toString()+ originalFilename.substring(originalFilename.lastIndexOf("."));

            pictureFile.transferTo(new File("D:\\upload\\"+FileName));

            product.setPic(FileName);
        }


        this.productService.edit(product);

        response.sendRedirect("/to_list");
    }

    @RequestMapping("/test")
    @ResponseBody
    public Product get(Product product){

        System.out.println(product);
        return product;
    }

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, HttpSession session){
        if(username.equals("admin") && password.equals("admin")){
            session.setAttribute("user",username);
            return "redirect:/to_list";
        }else{
            return "redirect:/to_login";
        }
    }

}
