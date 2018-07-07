package com.meetu.dto;

import lombok.Data;
import javax.persistence.*;

/**
 * 记录用户对新闻的评论，以及对评论的回复评论
 */

@Entity
@Data
public class UserNewsCommentDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long cid;//comment本身的id, todo：怎么记忆中好像JPA中所有数据表都必须有id这么一个字段？如果没有这个字段会启动失败？检测不到property id ？？？

    //todo: 对于评论系统而言不能只以这2个作为主键。。。因为一个用户可能对同一篇文章发布多次评论。
    //但是对于关注系统而言是没有问题的，因为用户只能关注这个app一次；
    private Long userid;
    private Long newsid;//评论回复新闻本身
    private Long cid_reply;//评论回复别人的评论

    private String content;

    private long ts;


    public UserNewsCommentDTO(){

    }

    public UserNewsCommentDTO(Long userid, Long newsid, Long cid_reply, String content, long ts){
        this.userid = userid;
        this.newsid = newsid;
        this.cid_reply = cid_reply;
        this.content = content;
        this.ts = ts;
    }

}
