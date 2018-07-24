package com.navi.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 存储用户上传的图片信息表
 */

@Entity
@Data
public class ImgDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String url;//评论内容
    private String remark;//图片说明


}
