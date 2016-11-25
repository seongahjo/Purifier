package com.skel.controller;

import com.skel.entity.App;
import com.skel.entity.Company;
import com.skel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

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


    // Login Page
    @RequestMapping("/")
    public ModelAndView mainPage() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    // Admin Page
    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("apps", appRepository.findAll());
        return mv;
    }

    // Main Page
    @RequestMapping("/main")
    public ModelAndView main(HttpSession session) {
        Company company = (Company) session.getAttribute("user");
        List<App> apps = appRepository.findByCompany(company);
        if (company == null)
            return new ModelAndView("redirect:/");
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("company", company);
        mv.addObject("mode", "user");
        mv.addObject("apps", apps);
        return mv;
    }

    // Detail Page
    @RequestMapping("/detail")
    public ModelAndView detail(HttpSession session) {
        Company company = (Company) session.getAttribute("user");
        if (company == null)
            return new ModelAndView("redirect:/");
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("mode", "detail");
        mv.addObject("company", company);
        return mv;
    }


    @RequestMapping("/register")
    public ModelAndView register(HttpSession session) {
        Company company = (Company) session.getAttribute("user");
        if (company == null)
            return new ModelAndView("redirect:/");
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("mode", "register");
        mv.addObject("company", company);
        return mv;
    }

    @RequestMapping("/clear")
    public ModelAndView close(HttpSession session) {
        Company company = (Company) session.getAttribute("user");
        if (company == null)
            return new ModelAndView("redirect:/");
        ModelAndView mv = new ModelAndView("main");
        mv.addObject("mode", "close");
        mv.addObject("company", company);
        return mv;
    }


    @RequestMapping("/slangs")
    public ModelAndView slang() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("slangs", slangRepository.findAll());
        return mv;
    }

    @RequestMapping("/pics")
    public ModelAndView pics() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("pics", picRepository.findAll());
        return mv;
    }

    @RequestMapping("/reports")
    public ModelAndView reports() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("reports", reportRepository.findAll());
        return mv;
    }

    @RequestMapping("/logs")
    public ModelAndView logs() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("logs", chatRepository.findAll());
        return mv;
    }

    @RequestMapping("/services/request")
    public ModelAndView requests() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("apps", appRepository.findByIsregister(false));
        return mv;
    }

    @RequestMapping("/services/quit")
    public ModelAndView quits() {
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("apps", appRepository.findByIsregister(true));
        return mv;
    }

}
