package com.navi.data;

import lombok.Data;

@Data
public class User {
    private String we_id;
    private String tel;
    private int age;
    private String remark;
//    private Map<String,String> respect;

    public User(String we_id,String tel, int age){
        this.we_id = we_id;
        this.tel = tel;
        this.age = age;
    }

}
