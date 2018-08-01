package com.navi.dto;

import lombok.Data;
//import org.springframework.data.annotation.Id;
import javax.persistence.*;


@Entity
@Data
public class UserDTO {

    @Id
    private long userid;//用户在本系统中唯一的不可变的ID,

    private String token;//todo: 最近登录被赋予的token...结合缓存机制进行用户权限验证...区别于传统桌面应用有账户密码，这个应该就相当于是用户的密码了？？

    //微信平台返回的数据
    private String session_key;//todo:这个我需要存储吗？有什么作用？

    private String openid;
    private String unionid;

    //private String grants;//当前用户已获取到的微信平台的授权列表

    //这个为主键是有问题的，同一个微信用户可能注册多次（就像当前数据库中序号为1，2的两个用户）。。。。看看其他解决方案把

    private String wechat;//wechat
    private String nickName;//用户微信昵称
    private int gender;
    private String avatar;//微信头像
    private String location;//微信地理位置
    //todo: 微信的getUserInfo还有哪些数据是我们这里需要保存的？


    private long createDate;//用户首次使用本小程序的时间(时间戳s)
    private long lastActive;//最近活跃


    private String notification;//list序列化成的字符串（应该是这样的：[notificationId, notificationId, notificationId]）

    public UserDTO(){}

    public UserDTO(Long userid){
        this.userid = userid;
    }

}
