package com.meetu.dto;

import lombok.Data;
//import org.springframework.data.annotation.Id;
import javax.persistence.Id;//这2个ID有什么区别？为什么用上面哪个就不对？push-notification中不就是用上面的这个吗？
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Entity
@Data
public class UserDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
    private Long id;

    private String email;//email，用于登录
    private String nickName;//用户昵称
    private String pwd;

    private String wechat;
    private String tel;

    private String school;
    private String major;//专业
    private Date birthday;

    private int sex;

    private String location;//地区

    private String signature;//个性签名

    private String portrait;//头像可以放到img标签的src中？？还是${user.venture}这种写法？

    //todo:头像：注册的时候按照hash,绝对值，取模然后从后端头像库中为用户随机生成一个，后续的时候用户可以上传文件修改头像；

    //todo: 电话，微信这些还是不要在个人资料页面展示，只在编辑用户资料的时候提供编辑，profile页面只展示（学校，专业，生日）等非敏感隐私信息

    //设置一个字段处理已认证这个选项
    //我们以微信号，手机号双重匹配。。。做一个二维码小页面，朋友圈营销："暗恋桃花源"。。。扫描二维码可以注册暗恋对象以及发现有几个人暗恋我，如果匹配上会得到通知（我们系统的message
    //为了支持这个"暗恋桃花源"的活动我们应该提供一个专门的ui。。。
    // 。扫描二维码直接就是微信登录并注册了，然后可以引导用户输入对方的微信号【或者】手机号，以及自己的微信号【以及】手机号这样可以进行匹配了

//    @Id
//    public Long getId(){
//        return this.id;
//    }
}
