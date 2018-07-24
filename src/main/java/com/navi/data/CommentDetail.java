package com.navi.data;

import lombok.Data;

@Data
public class CommentDetail {

    private String userName;
    private String userLogo;
    private String content;
    private String updateTime;

    public CommentDetail(){};

    public CommentDetail(String userName, String userLogo, String content, String updateTime){
        this.userName = userName;
        this.userLogo = userLogo;
        this.content = content;
        this.updateTime = updateTime;
    }
}
