package com.meetu.util;

import com.meetu.dto.AppDTO;
import com.meetu.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AppUtil {

    private static final int APPID_LENGTH = 6;//appid长度(appid仅仅由数字组成)

    @Autowired
    private AppService appService;

    //生成appid
    public long genAppid(){
        long appid = 0;

        while(true){
            appid = Long.parseLong(RandomString.getNumString(APPID_LENGTH));
            if(checkValid(appid) == true){
                break;
            }
        }

        return appid;
    }

    public boolean checkValid(long appid){//这里可能报空指针异常，怎么用java8的optional来解决这个问题呢？

        boolean valid = true;

        AppDTO appDTO = appService.findById(appid);

        if(appDTO != null){
            valid = false;
        }

        return valid;
    }

    public static void main(String args[]){
        //这样直接执行不可以，appService貌似不能成功注入（nullpointer）
        AppUtil appUtil = new AppUtil();
        long appid = appUtil.genAppid();
        System.out.println("appid:" + appid);
        boolean valid = appUtil.checkValid(appid);
        System.out.println("valid:" + valid);
    }
}
