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
    @JoinColumn(name = "useridx") // JOIN된 컬럼의 이름설정
    User user;

    @ManyToOne
    @JoinColumn(name = "appidx") // JOIN된 컬럼의 이름설정
    App app;
}
