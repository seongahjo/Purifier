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
    User user;

    @ManyToOne
    App app;
}
