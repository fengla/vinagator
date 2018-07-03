package com.meetu.data;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class App {

    //todo:因为有dto，所以这里这种数据结构是不是没必要？？
    private Long appId;
    private String appName;
    private String icon;
    private String qrCode;//二维码
    private String detail;//介绍，说明
    private Date updateDate;//更新日期

    private List<Integer> ct;
    private List<Integer> sct;//sctId name ctId(外键指明归属一级分类)
}
