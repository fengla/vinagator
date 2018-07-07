package com.meetu.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * app预览图
 */

@Entity
@Data
public class AppPreviewDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long previewId;

    private String img;//存储图片所在路径（这里填写相对路径就好，毕竟这种数据还是保存在本地服务器中，而不能依赖于外部其他人的服务器）

    private Long appid;
    //private int sort;//0,1,2,3,4预览图展示的先后顺序
    //todo: 对于同一app内部预览图展示的次序可以基于previewId进行sort(上传的时候就限制好按顺序方式插入数据库)


    public AppPreviewDTO(){}

    public AppPreviewDTO(Long previewId, String img){
        this.previewId = previewId;
        this.img = img;
    }

}
