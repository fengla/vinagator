package com.navi.dto;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 预览图
 */

@Entity
@Data
public class PreviewDTO {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String value;

    public PreviewDTO(){}

    public PreviewDTO(String value){
        this.value = value;
    }

}
