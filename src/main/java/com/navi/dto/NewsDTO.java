package com.navi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NewsDTO {


    @Id
    private String docid;//本地docid，当前也就是一点的docid, 后续可以再修改

    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


    private String content;
    private String img;

    private long ts;
    private Boolean isGif;//Boolean -> booleans iGif这个字段有什么用？是不是可以不要了？

    @JsonIgnore //此注解的属性，不会被序列化为json传到前端
    private String remark;

    @Transient
    private boolean containsImg;

    private int follows;//赞的人数
    private int unfollows;//踩的人数
}
