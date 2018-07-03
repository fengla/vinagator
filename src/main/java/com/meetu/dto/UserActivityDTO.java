package com.meetu.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 记录用户和活动之间关系的数据表
 */

@Entity
@Data
public class UserActivityDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long userId;//评论内容
    private Long activityId;//图片说明

    //springData怎么做外键关联？。。。如果我不用外键关联，直接join查询不是也可以实现我的功能的吗？有什么缺点


}
