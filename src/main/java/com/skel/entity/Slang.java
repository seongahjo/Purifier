package com.skel.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by hootting on 2016. 10. 11..
 */
@Data
@Entity
public class Slang {
    @Id
    @GeneratedValue
    @Column(name="slangidx")
    Integer slangIdx;

    @Column(name="word")
    String word;

    @Column(name="count")
    Integer count;

    public Slang(){
        word="";
        count=0;
    }
    public Slang(String word){
        this.count=0;
        this.word=word;
    }

}
