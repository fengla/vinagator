package com.meetu.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//import org.springframework.data.annotation.Id;

/**
 * 一级分类
 */

@Entity
@Data
public class BannerDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
    private Long id;

    private String name;//名称
    private String img;//物料大图
    private String url;//跳转地址（路径）

    private String remark;//说明备注

    //todo:因为要修改颜色的嘛，看看这个颜色数据是写到remark里面（css@@others@@others这样）还是另外再定义一个字段？似乎没必要另外定义字段？毕竟当前这个remark也没什么用

    private long updateTime;//更新时间

    public BannerDTO(){}

    public BannerDTO(Long id, String name, String img, String url){
        this.id = id;
        this.name = name;
        this.img = img;
        this.url = url;
    }


}
