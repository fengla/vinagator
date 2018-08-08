package com.navi.util;


import java.util.HashMap;
import java.util.Map;

public class OpenidUtilTest {
    public static void main(String args[]) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("appid", "");
        params.put("secret", "");
        params.put("js_code", "033UTz1Q023gBa21d95Q0UcH1Q0UTz1j");
        params.put("grant_type", "authorization_code");

        String resp = HttpConnectionUtils.getGetResult("https://api.weixin.qq.com/sns/jscode2session?", params);
        System.out.println("resp:" + resp);
        //resp:{"session_key":"aAf3uQ5AslIObWmLT+BXPg==","openid":"oFoSZ5T0DOf8Fhfz2SZfWcaJW-b0"}
        //第二次请求会报错，因为js_code这个参数只能使用一次：resp:{"errcode":40163,"errmsg":"code been used, hints: [ req_id: 7L8aBa05974105 ]"}



    }
}
