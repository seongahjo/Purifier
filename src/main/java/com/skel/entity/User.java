package com.skel.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
    @JsonBackReference(value="app-user")
    App app;

    @OneToMany(mappedBy="user")
    @JsonManagedReference(value="user-chat")
    List<Chat> chats;

    @OneToMany(mappedBy="user")
    @JsonManagedReference(value="user-pic")
    List<Pic> pics;

    @OneToMany(mappedBy="user")
    @JsonManagedReference(value="user-report")
    List<Report> reports;


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
