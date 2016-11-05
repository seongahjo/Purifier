package com.skel.entity;

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
    User user;

    @ManyToOne
    @JoinColumn(name = "appidx") // JOIN된 컬럼의 이름설정
    App app;

    public Chat(){}
    public Chat(String content,User u,App a){
        this.content=content;
        this.user=u;
        this.app=a;
    }
}
