package com.meetu.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//import org.springframework.data.annotation.Id;


@Entity
@Data
public class CommentDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
    private Long id;

    private String content;//评论内容
    private Long activityId;//所评论的活动的id
    private Long userId;//用户id
    private Date updateTime;//更新时间

    public CommentDTO(){}

    public CommentDTO(String content, Long activityId, Long userId, Date updateTime){
        this.content = content;
        this.activityId = activityId;
        this.userId = userId;
        this.updateTime = updateTime;
    }


}
