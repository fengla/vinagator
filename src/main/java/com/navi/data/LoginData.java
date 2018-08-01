package com.navi.data;

import lombok.Data;

@Data
public class LoginData {

    private boolean firstLogin;
    private String token;
    private long userid;

    public LoginData(boolean firstLogin, String token, long userid){
        this.firstLogin = firstLogin;
        this.token = token;
        this.userid = userid;
    }
}
