package com.navi.data.response;

import lombok.Data;

/**
 * 用户点赞，点踩后的响应信息
 */

@Data
public class CommonResp {

    //关于点赞：0:success, 1:已经点过赞了, 2:已经点过踩了, -1:unknown（未知错误）
    //关于用户自行提交app:
    //关于todo
    private int code=0;
    private String desc;

    public CommonResp(){}

    public CommonResp(int code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
