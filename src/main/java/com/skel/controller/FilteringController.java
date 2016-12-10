package com.skel.controller;

import com.skel.entity.*;
import com.skel.repository.AppRepository;
import com.skel.repository.ChatRepository;
import com.skel.repository.PicRepository;
import com.skel.repository.UserRepository;
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
        User u = userRepository.findById(chat.getUser().getId());
        Company company = app.getCompany();
        Chat newchat = null;

        if (u == null) { // user가 존재하지 않을경우 User를 추가해줌
            u = new User(chat.getUser().getId());
            u.setApp(app);
        }
        app.setTotalcount(app.getTotalcount() + 1);

        log.info("Filtering Input : " + chat.toString());
        String content = chat.getContent();
        String newContent = null;
        newchat = new Chat(content, u, app);

        userRepository.saveAndFlush(u);
        chatRepository.saveAndFlush(newchat);
        appRepository.saveAndFlush(app);

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
                File temp = new File(path + "/" + file.getOriginalFilename());
                file.transferTo(temp);
                // 임시 파일 업로드
                log.info("temp file upload");
                Isfilter = FilterUtil.filterPicture(path + "/" + file.getOriginalFilename());
                company.setCountPicture(company.getCountPicture() + 1);
                // if (Isfilter)
                //     upload(app, u, file);
                // temp.delete();
                newchat.setIsfilter(Isfilter);
            } catch (IOException e) {

            }
        }
        newContent = FilterUtil.filterSlang(content);
        if (!newContent.equals(content)) {
            app.setFiltercount(app.getFiltercount() + 1);
            company.setCountSlang(company.getCountSlang() + 1);
            u.setCountSlang(u.getCountSlang() + 1); // count회수 추가
        }
        newchat.setContent(newContent);
        log.info("Filtering End");
        return ResponseEntity.ok(newchat);
    }

    private Pic upload(App a, User u, MultipartFile file) {
        String folderPath = FilterUtil.path + "/upload"; // 폴더 경로
        String fileName = folderPath + "/" + file.getOriginalFilename();
        log.info(fileName);
        try {
            File folder = new File(folderPath);
            if (!folder.exists())
                folder.mkdirs();
            File transferFile = new File(fileName);
            file.transferTo(transferFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pic pics = new Pic(fileName, u, a);
        return picRepository.save(pics);
    }


}
