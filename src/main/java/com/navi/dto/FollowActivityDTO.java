package com.navi.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 *存储用户与其感兴趣的活动的关系的表
 */

@Entity
@Data
public class FollowActivityDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long activityId;//感兴趣的活动的id
    private Long userId;//用户id


}
