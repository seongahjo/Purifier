package com.skel.controller;

import com.skel.entity.App;
import com.skel.entity.Chat;
import com.skel.entity.User;
import com.skel.repository.AppRepository;
import com.skel.repository.ChatRepository;
import com.skel.repository.UserRepository;
import com.skel.util.FilterUtil;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    @Transactional(propagation= Propagation.REQUIRED, readOnly=false)
    public ResponseEntity filter(@RequestBody Chat chat) {
        App app = appRepository.findOne(chat.getApp().getAppIdx());
        if(app == null) // app이 존재하지 않을경우 예외처리
            return ResponseEntity.badRequest().body("App Not Exists");
        User u = userRepository.findById(chat.getUser().getId());

        Chat newchat = null;
        if (u == null) { // user가 존재하지 않을경우 User를 추가해줌
            u = new User(chat.getUser().getId());
            u.setApp(app);
        }
        u.setCountSlang(u.getCountSlang()+1); // count회수 추가
        log.info("Filter Input : " + chat.toString());
        String content = chat.getContent();
        newchat = new Chat(content, u, app);
        userRepository.saveAndFlush(u);
        chatRepository.saveAndFlush(newchat);
        log.info("Filtering start");
        content = FilterUtil.filterSlang(content);
        newchat.setContent(content);

        //Filter된 내용을 저장? Filter 전 내용을 저장?*/
        return ResponseEntity.ok(newchat);
    }
}
