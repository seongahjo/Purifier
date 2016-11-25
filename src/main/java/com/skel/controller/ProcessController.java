package com.skel.controller;

import com.skel.entity.Company;
import com.skel.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by hootting on 2016. 11. 25..
 */
@RestController
@Slf4j
public class ProcessController {
    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping("/login")
    ResponseEntity login(HttpSession session, @ModelAttribute("company") Company company) {
        log.info("login attempt : " + company.getId() + " " + company.getPw());
        Company dbCompany = companyRepository.findById(company.getId());
        if (dbCompany == null)
            return ResponseEntity.badRequest().body("Not Exists");
        if (dbCompany.equals(company)) {
            session.setAttribute("user", dbCompany);
            log.info("login succeed : " + company.getId());
            return ResponseEntity.ok("login succeed");
        } else {
            return ResponseEntity.badRequest().body("pw Error");
        }
    }
}
