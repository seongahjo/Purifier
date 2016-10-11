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
    App app;

    @OneToMany(mappedBy="user")
    Set<Chat> chats;

    @OneToMany(mappedBy="user")
    Set<Report> reports;


}
