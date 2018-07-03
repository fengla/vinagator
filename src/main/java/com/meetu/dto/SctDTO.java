package com.meetu.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//import org.springframework.data.annotation.Id;

/**
 * 二级分类
 * todo：二级 分类目前暂且没用到，先这么定义吧，后续再处理应用逻辑
 */

@Entity
@Data
public class SctDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO) //todo:分别理解几种生成机制
    private Long id;

    private Long ctId;//外键 （todo:springData这里怎么注解外键）

    private String name;//名称
    private String icon;//图标（路径）
    private String url;//跳转地址（路径）

    private long updateTime;//更新时间

    public SctDTO(){}

    public SctDTO(Long id, String name, String icon, String url){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
    }


}
