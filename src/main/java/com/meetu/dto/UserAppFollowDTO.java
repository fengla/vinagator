package com.meetu.dto;

import com.meetu.data.UserAppFollowKey;
import lombok.Data;

import javax.persistence.*;


/**
 * 记录用户与app关注关系的表
 */

@Entity
@Data
@IdClass(UserAppFollowKey.class)
public class UserAppFollowDTO {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
//    private Long id;
    @Id
    private Long userid;//名称 todo:这里如果是long会有问题吗？毕竟DTO中定义的是Long
    @Id
    private Long appid;//图标（路径）保留字段，现在用css样式控制，这个可以暂时废弃不用

    private long ts;


    public UserAppFollowDTO(){

    }

    public UserAppFollowDTO(Long userid, Long appid, long ts){
        this.userid = userid;
        this.appid = appid;
        this.ts = ts;
    }

    //todo: userid + appid 构成这个数据表的主键（不需要再冗余设计一个单独属于这个表的主键，看看这样是否可以实现）
    //todo: springData jpa中如何注解设计外键关系，这里的userid,appid都分别是另外两张表的主键

    //todo: 如果只在数据库端用navigate定义外键关系，这里可以正常存储吗？如果这里存储了一个数据但是在用户与app本身的表中却没有数据，现在测试一下
}
