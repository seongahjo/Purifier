package com.skel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(name="useridx")
    Integer userIdx;

    @Column(name="id")
    String id;

    @Column(name="countSlang")
    Integer countSlang;

    @Column(name="countPicture")
    Integer countPicture;

    @ManyToOne
    @JoinColumn(name = "appidx") // JOIN된 컬럼의 이름설정
    App app;

    @OneToMany(mappedBy="user")
    Set<Chat> chats;

    @OneToMany(mappedBy="user")
    Set<Report> reports;

    public User(){
        this.countSlang=0;
        this.countPicture=0;
    }
    public User(String userid){
        this.id=userid;
        this.countSlang=0;
        this.countPicture=0;
    }
}
