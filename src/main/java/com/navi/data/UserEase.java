package com.navi.data;

import lombok.Data;

@Data
public class UserEase {

    //用户id
    private Long id;

    //请求token(当前设计是每个用户每次登录有效，有效期24小时？？暂且这么实现吧。。)
    private String token;
}
