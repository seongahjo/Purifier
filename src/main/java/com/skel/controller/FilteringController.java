package com.skel.controller;

import com.skel.entity.*;
import com.skel.repository.*;
import com.skel.util.FilterUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by hootting on 2016. 11. 3..
 */
@Log
@RestController
public class FilteringController {


    @Autowired
    ChatRepository chatRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Autowired
    PicRepository picRepository;


    @Autowired
    ReportRepository reportRepository;


    @Autowired
    SlangRepository slangRepository;

    @Autowired
    BadpicRepository badpicRepository;
    @Autowired
    CompanyRepository companyRepository;

    /*
        Filter
        REST API
        @Case
        게시판
        @Paramter
        appidx : 앱 PK
        userid : 유저아이디
        content : 필터링하는 내용
     */
    //java.lang.UnsatisfiedLinkError.class


    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    // request header : x-www-form-urlencoded
    public ResponseEntity filter(@ModelAttribute("chat") Chat chat, @RequestParam(value = "pic", required = false) MultipartFile file) {
        App app = appRepository.findOne(chat.getApp().getAppIdx());
        if (app == null) // app이 존재하지 않을경우 예외처리
            return ResponseEntity.badRequest().body("App Not Exists");
        Company company = app.getCompany();
        Chat newchat = null;
        String filename = null;
        User u = userRepository.findById(chat.getUser().getId());
        if (u == null) { // user가 존재하지 않을경우 User를 추가해줌
            u = new User(chat.getUser().getId());
            u.setApp(app);
        }
        app.setTotalcount(app.getTotalcount() + 1);

        log.info("Filtering Input : " + chat.toString());
        String content = chat.getContent();
        String newContent = null;
        newchat = new Chat(content, u, app);



        log.info("Filtering start");
        // 사진 Filtering
        if (file != null) {
            log.info("Pic");
            try {
                String path = FilterUtil.path + "/tmp";
                Boolean Isfilter = null;
                File tempFolder = new File(path);
                if (!tempFolder.exists())
                    tempFolder.mkdirs();
                app.setTotalcount(app.getTotalcount() + 1);
                app.setFiltercount(app.getFiltercount() + 1);
                filename = path + "/" + file.getOriginalFilename();
                File temp = new File(filename);
                file.transferTo(temp);
                // 임시 파일 업로드
                log.info("temp file upload");
                Isfilter = FilterUtil.filterPicture(filename);
                company.setCountPicture(company.getCountPicture() + 1);
                log.info(Isfilter+ " ");
                if (Isfilter) {
                    upload(app, u, filename);
                    u.setCountPicture(u.getCountPicture() + 1); // count회수 추가
                    app.setFiltercount(app.getFiltercount()+1);
                }
                else
                    temp.delete();
                newchat.setIsfilter(Isfilter);
            } catch (IOException e) {

            }
        }
        newContent = FilterUtil.filterSlang(content);
        company.setCountSlang(company.getCountSlang() + 1);
        if (!newContent.equals(content)) {
            app.setFiltercount(app.getFiltercount() + 1);
            u.setCountSlang(u.getCountSlang() + 1); // count회수 추가
        }
        newchat.setContent(newContent);
        log.info("Filtering End");
        userRepository.saveAndFlush(u);
        chatRepository.saveAndFlush(newchat);
        appRepository.saveAndFlush(app);
        companyRepository.saveAndFlush(company);
        return ResponseEntity.ok(newchat);
    }


    private Pic upload(App a, User u, String url) {
        Pic pics = new Pic(url, u, a);
        return picRepository.saveAndFlush(pics);
    }

    @RequestMapping(value = "/report", method = RequestMethod.POST)
    public ResponseEntity report(@ModelAttribute Report report ){
        log.info(report.getUser().getId());
        User u = userRepository.findById(report.getUser().getId());
        if (u == null) { // user가 존재하지 않을경우 User를 추가해줌
            u = new User(report.getUser().getId());

        }
        report.setUser(u);
        userRepository.saveAndFlush(u);
        reportRepository.saveAndFlush(report);
        return ResponseEntity.ok("good");
    }



    @RequestMapping(value = "/report/{reportIdx}", method = RequestMethod.GET)
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public ResponseEntity report(@PathVariable("reportIdx")Integer reportIdx ){
        Report report = reportRepository.findOne(reportIdx);
        if(report.getType()==0) {
            Slang slang = new Slang(report.getContent());
            slangRepository.saveAndFlush(slang);
        }
        else{
            String content="/Users/hootting/Desktop/exp/public/"+report.getContent();
            Badpic badpic=new Badpic(content);
            badpicRepository.saveAndFlush(badpic);
        }
        reportRepository.delete(report);
        return ResponseEntity.ok("good");
    }

    @RequestMapping(value = "/report/{reportIdx}/no", method = RequestMethod.GET)
    public ResponseEntity reportno(@PathVariable("reportIdx")Integer reportIdx ){

        reportRepository.delete(reportIdx);
        return ResponseEntity.ok("good");
    }
}
