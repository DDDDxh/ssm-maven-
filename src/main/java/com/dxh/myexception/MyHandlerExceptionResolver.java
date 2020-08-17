package com.dxh.myexception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        String aa = "傻逼出bug了";

        if(e instanceof MyException){
            aa = ((MyException) e).getMsg();
        }


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMsg",aa);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
