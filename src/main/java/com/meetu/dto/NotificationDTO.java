//package com.meetu.dto;
//
//import lombok.Data;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import java.util.Date;
//
//@Entity
//@Data
//public class NotificationDTO {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
//    private long notifid;//通知的ID
//
//    private String content;//通知内容
//    private boolean read;//true or false (true for read already)
//
//    private Date expireDate;
//    //过期时间，每天闲时定时任务清理已过期的通知消息（这里需要清理两个表：1.先清理用户表的notification字段，把所有这个notification id的数据清理掉 2.再清理notification表本身（需要注意维护一致性，防止用户侧没有删除掉））
//    //现在这种外键关系的一致性这些都是我再业务代码中来控制。这里如何保证一致性？？？（先实现功能吧，后面再考虑这些问题。）
//
//    private long ts;//更新时间
//
//    public NotificationDTO(){}
//
//    public NotificationDTO(String content, long ts){
//        this.content = content;
//        this.ts = ts;
//    }
//
//}
