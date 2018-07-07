package com.meetu.dto;

import lombok.Data;
//import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.util.Date;


@Entity
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userid;//用户在本系统中唯一的不可变的ID,

    //这个为主键是有问题的，同一个微信用户可能注册多次（就像当前数据库中序号为1，2的两个用户）。。。。看看其他解决方案把

    private String wechat;//wechat
    private String nickName;//用户微信昵称

    public UserDTO(Long userid, String wechat, String nickName){
        this.userid = userid;
        this.wechat = wechat;
        this.nickName = nickName;
    }

}
