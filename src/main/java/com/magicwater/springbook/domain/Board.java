package com.magicwater.springbook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Board {
    
    @Id
    @GeneratedValue
    private Long seq;
    private String title;
    // private String writer; 
    private String content;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createDate = new Date();
    @Column(updatable = false)
    private Long cnt = 0L;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false, updatable = false)
    private Member member;

    public void setMember(Member member){
        this.member = member;
        member.getBoardList().add(this);
    }
}