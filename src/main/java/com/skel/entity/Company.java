package com.skel.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class Company {
    @Id
    @GeneratedValue
    @Column(name="companyidx")
    Integer companyIdx;

    @Column(name="name")
    String name;

    @Column(name="type")
    String type;

    @Column(name="countSlang")
    Integer countSlang;

    @Column(name="countPicture")
    Integer countPicture;

    @OneToMany(mappedBy="company")
    Set<App> apps;

}
