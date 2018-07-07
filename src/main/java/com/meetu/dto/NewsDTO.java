package com.meetu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class NewsDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String docid;//本地docid，当前也就是一点的docid, 后续可以再修改

    private String content;
    private String img;

    private long ts;
    private Boolean isGif;//Boolean -> booleans iGif这个字段有什么用？是不是可以不要了？

    @JsonIgnore //此注解的属性，不会被序列化为json传到前端
    private String remark;

    @Transient
    private boolean containsImg;
}
