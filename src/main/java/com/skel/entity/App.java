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
    @JsonManagedReference(value="app-user")
    List<User> users;

    @ManyToOne
    @JoinColumn(name = "companyidx") // JOIN된 컬럼의 이름설정
    @JsonBackReference(value="company-app")
    Company company;

    @OneToMany(mappedBy="app")
    @JsonManagedReference(value="app-chat")
    List<Chat> chats;

    @OneToMany(mappedBy = "app")
    @JsonManagedReference(value="app-pic")
    List<Pic> pics;

}
