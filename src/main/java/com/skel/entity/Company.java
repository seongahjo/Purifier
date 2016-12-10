package com.skel.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
@EqualsAndHashCode(of={"id","pw"})
public class Company {
    @Id
    @GeneratedValue
    @Column(name="companyidx")
    Integer companyIdx;

    @Column(name="id")
    String id;

    @Column(name="pw")
    String pw;

    @Column(name="name")
    String name;

    @Column(name="type")
    String type;

    @Column(name="countSlang")
    Integer countSlang=0;

    @Column(name="countPicture")
    Integer countPicture=0;

    @OneToMany(mappedBy="company")
    @JsonIgnore
    List<App> apps;

}
