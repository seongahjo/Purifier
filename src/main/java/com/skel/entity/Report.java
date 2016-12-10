package com.skel.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by hootting on 2016. 10. 11..
 */

@Data
@Entity
public class Report {
    @Id
    @GeneratedValue
    @Column(name="reportidx")
    Integer reportIdx;

    @Column(name="type")
    Integer type; //0 비속어 1 사진

    @Column(name="content")
    String content;

    @ManyToOne
    @JoinColumn(name = "useridx")  // JOIN된 컬럼의 이름설정
    @JsonIgnore
    User user;
}
