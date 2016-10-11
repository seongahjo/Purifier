package com.skel.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class Pic {
    @Id
    @GeneratedValue
    @Column(name="picidx")
    Integer picIdx;

    @Column(name="url")
    String url;

    @ManyToOne
    User user;

    @ManyToOne
    App app;
}
