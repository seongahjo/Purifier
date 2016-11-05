package com.skel.controller;

import com.skel.entity.Chat;
import com.skel.repository.AppRepository;
import com.skel.repository.ChatRepository;
import com.skel.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        @Paramter
        appidx : 앱 PK
        userid : 유저아이디
        content : 필터링하는 내용
     */




    @RequestMapping(value="/filter",method= RequestMethod.POST)
    ResponseEntity filter(@RequestBody Chat chat) {
        log.info("test");
        log.info(chat.toString());
        /*
        App app = appRepository.getOne(appidx.longValue());
        if (app == null)
            return ResponseEntity.badRequest().body("Wrong App");
        User user = userRepository.findById(userid);
        user.setApp(app);
        if (user == null)
            user = new User(userid);

        log.info("filtering start");
        content=FilterUtil.filterSlang(content);

        // 사진 필터링
        if (file != null) {

        }

        userRepository.saveAndFlush(user);
        chatRepository.saveAndFlush(new Chat(content, user, app));
        //Filter된 내용을 저장? Filter 전 내용을 저장?*/
        return ResponseEntity.ok("good");
    }

    @RequestMapping(value="/test", method=RequestMethod.POST)
    String test(@RequestParam("test") Integer test) {
        log.info("test" + test);
        return "test";
    }
}
