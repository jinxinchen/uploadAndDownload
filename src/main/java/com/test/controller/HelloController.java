package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 陈 on 2017/9/17.
 */
@Controller
@RequestMapping("/home")
public class HelloController {
    @RequestMapping("/index")
    public String show(){
        return "index";
    }
    @RequestMapping("/query")
    public ModelAndView query(){
        ModelAndView mv = new ModelAndView();
        System.out.println("on...");
        mv.setViewName("index");
        return mv;
    }
}
