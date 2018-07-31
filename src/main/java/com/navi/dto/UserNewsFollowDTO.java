package com.navi.dto;

import com.navi.data.UserNewsFollowKey;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;


/**
 * 记录用户与app关注关系的表
 */

@Entity
@Data
@IdClass(UserNewsFollowKey.class)
public class UserNewsFollowDTO {

    @Id
    private long userid;
    @Id
    private String newsid;

    private int follow;//follow:0:default保留字段; 1:点赞; -1：点踩

    private long ts;


    public UserNewsFollowDTO(){

    }

    public UserNewsFollowDTO(Long userid, String newsid, int follow, long ts){
        this.userid = userid;
        this.newsid = newsid;
        this.follow = follow;
        this.ts = ts;
    }

}
