package com.skel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by seongahjo on 2016. 12. 11..
 */
@Data
@Entity
public class Badpic {
    @Id
    @GeneratedValue
    @Column(name="badpicidx")
    Integer badpicIdx;

    @Column(name="url")
    String url;

    public Badpic(){url="";}
    public Badpic(String url){this.url=url;}

}
