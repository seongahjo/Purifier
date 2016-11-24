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
        ModelAndView mv=new ModelAndView("login");
        mv.addObject("name","seongah");
        return mv;
    } // return MainPage

    @RequestMapping("/admin")
    public ModelAndView admin(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("name","admin");
        return mv;
    }

    @RequestMapping("/main")
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("name","seongah");
        return mv;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(){
        ModelAndView mv = new ModelAndView("detail");
        mv.addObject("name","seongah");
        mv.addObject("mode","detail");
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("detail");
        mv.addObject("name","seongah");
        mv.addObject("mode","detail");
        return mv;
    }
}
