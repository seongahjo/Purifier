package com.skel.controller;

import com.skel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Controller
public class MainController {
    @Autowired
    AppRepository appRepository;

    @Autowired
    SlangRepository slangRepository;

    @Autowired
    PicRepository picRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ReportRepository reportRepository;


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

    @RequestMapping("/list")
    public ModelAndView register(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("name","seongah");
        mv.addObject("apps",appRepository.getOne(1));
        return mv;
    }
    @RequestMapping("/slangs")
    public ModelAndView slang(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("name","admin");
        mv.addObject("slangs",slangRepository.findAll());
        return mv;
    }

    @RequestMapping("/pics")
    public ModelAndView pics(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("name","admin");
        mv.addObject("pics",picRepository.findAll());
        return mv;
    }

    @RequestMapping("/reports")
    public ModelAndView reports(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("name","admin");
        mv.addObject("reports",reportRepository.findAll());
        return mv;
    }

    @RequestMapping("/logs")
    public ModelAndView logs(){
        ModelAndView mv = new ModelAndView("list");
        mv.addObject("name","admin");
        mv.addObject("logs",chatRepository.findAll());
        return mv;
    }
}
