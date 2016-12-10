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
// postgresql user테이블 생성
// postgresql에서 "user"이여야함
@Table(name="\"user\"")
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
    @JsonIgnore
    App app;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    List<Chat> chats;

    @OneToMany(mappedBy="user")
    @JsonIgnore
    List<Pic> pics;

    @OneToMany(mappedBy="user")
    @JsonIgnore
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
