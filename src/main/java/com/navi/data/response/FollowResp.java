package com.navi.data.response;

import lombok.Data;

/**
 * 用户点赞，点踩后的响应信息
 */

@Data
public class FollowResp {

    private int code=0;//0:success, 1:已经点过赞了, 2:已经点过踩了, -1:unknown（未知错误）
    private long userid;
    private String newsid;

    public FollowResp(){};

    public FollowResp(int code, long userid, String newsid){
        this.code = code;
        this.userid = userid;
        this.newsid = newsid;
    }
}
