package com.navi.dto;

import lombok.Data;
//import org.springframework.data.annotation.Id;
import javax.persistence.*;


@Entity
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userid;//用户在本系统中唯一的不可变的ID,

    //微信平台返回的数据
    private String session_key;

    private String openid;
    private String unionid;

    //private String grants;//当前用户已获取到的微信平台的授权列表

    //这个为主键是有问题的，同一个微信用户可能注册多次（就像当前数据库中序号为1，2的两个用户）。。。。看看其他解决方案把

    private String wechat;//wechat
    private String nickName;//用户微信昵称

    private long createDate;//用户首次使用本小程序的时间(时间戳s)

    private long lastActive;//最近活跃
    private String token;//todo: 最近登录被赋予的token...结合缓存机制进行用户权限验证

    //todo: 其他从微信端获取的数据这里再进行定义


    private String notification;//list序列化成的字符串（应该是这样的：[notificationId, notificationId, notificationId]）

    public UserDTO(Long userid, String wechat, String nickName){
        this.userid = userid;
        this.wechat = wechat;
        this.nickName = nickName;
    }

}
