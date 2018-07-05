//package com.meetu.dto;
//
//import lombok.Data;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//
///**
// * 记录用户与新闻评论关系的表
// */
//
//@Entity
//@Data
//public class UserNewsCommentDTO {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
//    private Long id;
//
//    private String name;//名称
//    private String icon;//图标（路径）保留字段，现在用css样式控制，这个可以暂时废弃不用
//    private String css;//css样式
//    private String url;//跳转地址（路径）
//
//    private long  updateTime;//更新时间
//
//    public UserNewsCommentDTO(){}
//
//    public UserNewsCommentDTO(Long id, String name, String icon, String url){
//        this.id = id;
//        this.name = name;
//        this.icon = icon;
//        this.url = url;
//    }
//
//}
