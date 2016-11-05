package com.skel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public ModelAndView mainPage(){
        ModelAndView mv=new ModelAndView("index");
        mv.addObject("name","seongah");
        return mv;
    } // return MainPage
}
