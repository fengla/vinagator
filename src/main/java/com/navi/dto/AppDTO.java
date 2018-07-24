package com.navi.dto;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//import org.springframework.data.annotation.Id;

/**
 * 一级分类
 */

@Entity
@Data
public class AppDTO {

    //todo:springData后续这个DTO发生改变可以影响传递到mysql中吗？增加字段，删除字段，修改字段名都可以？

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
    private Long appid;

    private String appName;
    private String icon;//图标
    private String qrCode;//二维码
    private String summary;//摘要，简介
    private String detail;//介绍，说明

    private String dev;//开发商。。。用户提交的时候可以不填写（就不要在提交页面展示这个好了），后期编辑审核的时候补全这个信息
    private String updateUser;//提交信息的用户的id ?关于小程序的关注用户我可以获取到哪些信息呢 ？
    private long updateDate;//更新日期，单位: s

//    @Column(name = "ct")
//    private Set<Integer> ct;//todo: 在app上传页面中以多选的样式给出来，方便用户多选提交
//    @Column(name = "sct")
//    private Set<Integer> sct;//sctId ... ctId(外键指明归属一级分类)
    //todo:list类型通过springData存储到mysql中是什么样子？后面又该如何解析成list返回服务端进而返回前端？
    private int ct;//逗号分隔？？这样还是不行。。没办法实现分类别展示。。。如果只能属于一个类别这样倒是也可以。。目前的设计就让一个app只能属于一个类别吧!
    private int sct;//这里直接指ct,sct的id...springData这种如何注解出外键关系
    //前端页面先选择一级分类，如果该一级分类下有二级分类那么才展示二级分类

    @Transient //...先不加注解看看会如何存储？
    private String ctName;

    @Transient //...先不加注解看看会如何存储？
    private List<String> previews;//预览图(这个不持久化，但是需要在前端展示)
    private String previewStr;//预览图（这个需要持久化，但是不在前端展示）

    private String remark;//备注信息，保留字段，仅仅用户管理员查看，不向用户暴露

    private double score;//评分，app在各类榜单下的排序依据
    //如果在不同榜单下排序依据不一样，后续可以再做score2,score3等，目前只维护一个score就可以了

    private boolean valid;//审核字段，编辑审核合格后再设置为true todo:这样子，自己不主动设置的话，默认值是true还是false ???

    public AppDTO(){}

    public AppDTO(String name, String detail){
        this.appName = name;
        this.detail = detail;
    }

    //亟待处理web端上传app的需求。。。
    //周末看看怎么发布，申请空间域名准备备案等；
    //下周处理完就可以申请审核上线了


}
