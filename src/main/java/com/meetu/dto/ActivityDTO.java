package com.meetu.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//import org.springframework.data.annotation.Id;


@Entity
@Data
public class ActivityDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //分别理解几种生成机制
    private Long id;

    private String name;//活动名称
    private String date;//日期
    private String location;//地点

    private String charge;
    private int chargeDetail;
    // 要不要设计成外键呢？
    // 对于站内发布的活动置为外键？
    // todo: 对于站外活动不设置成外键？这样还是比较合理的。体现在前端站内发起的这个发起人可以点击，站外发起的这里不可以点击
    private int sponsor;

    private int interested;

    private String logo;//todo:活动封面。。。对于封面我需要做一个后台自动调整图片比例的插件。。后台服务。。。
    private String imgs;//todo：这存图片id,另外一张图片表指定（图片id->图片url）

    //String类型有没有字数限制？这样能不能完全接收到数据库中？
    //对于加粗啊，颜色啊，表情啊那些文本属性可以正常存到数据库吗？
    private String details;

    //是否保留这个活动须知？tips
    private String tips;

    public ActivityDTO(){}

    public ActivityDTO(String name, String date, String location, String charge, int chargeDetail, int sponsor, String details, String tips){
        this.name = name;
        this.date = date;
        this.location = location;
        this.charge = charge;
        this.chargeDetail = chargeDetail;
        this.sponsor = sponsor;
        this.details = details;
        this.tips = tips;
    }


}
