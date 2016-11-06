package com.skel.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class Chat {
    @Id
    @GeneratedValue
    @Column(name="chatidx")
    Integer chatIdx;

    @Column(name="content")
    String content;

    @ManyToOne
    @JoinColumn(name = "useridx") // JOIN된 컬럼의 이름설정
    @JsonBackReference(value="user-chat")
    User user;

    @ManyToOne
    @JoinColumn(name = "appidx") // JOIN된 컬럼의 이름설정
    @JsonBackReference(value="app-chat")
    App app;

    public Chat(){}
    public Chat(String content,User u,App a){
        this.content=content;
        this.user=u;
        this.app=a;
    }

}
