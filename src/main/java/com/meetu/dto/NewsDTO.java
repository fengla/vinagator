package com.meetu.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class NewsDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String docid;//本地docid，当前也就是一点的docid, 后续可以再修改

    private String content;
    private String img;
    private String remark;
    private long ts;
    private Boolean isGif;
}
