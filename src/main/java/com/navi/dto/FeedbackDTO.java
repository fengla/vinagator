package com.navi.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import org.springframework.data.annotation.Id;

/**
 * 意见与反馈
 */

@Entity
@Data
public class FeedbackDTO {

    @Id
    private long id;

    private String content;//反馈内容
    private long userid;
    private boolean replied;//true=已回复

    private long  updateDate;//更新时间

    public FeedbackDTO(){}

    public FeedbackDTO(Long id, String content){
        this.id = id;
        this.content = content;
    }

}
