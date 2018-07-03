package com.meetu.data;

import lombok.Data;

import java.util.Date;

/**
 *说明：用于首页展示的信息，因为只需要这 一点点数据，其他数据完全没必要上传，所以定义这个一个结构出来
 */


@Data
public class ActivityOutline {

    //todo:后续需要在ActivityRepository中自定义查询语句  @query(select name,id from activity;)这样

    private Long id;

    private String name;//活动名称
    private Date date;//日期
    private String location;//地点
    private String charge;
    private String sponsor;

    private String logo;
}
