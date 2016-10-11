package com.skel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class App {
    @Id
    @GeneratedValue
    @Column(name="appidx")
    Integer appIdx; //

    @Column(name="name")
    String name; // 이름

    @Column(name="type")
    String type; // 어떤 종류의 앱인지, 게임?

    @OneToMany(mappedBy="app")
    Set<User> users;

    @ManyToOne
    Company company;

    @OneToMany(mappedBy="user")
    Set<Chat> chats;

    @OneToMany(mappedBy = "user")
    Set<Pic> pics;

}
