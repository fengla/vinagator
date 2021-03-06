package com.navi.util;

import com.navi.dto.UserDTO;
import com.navi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserUtil {

    private static final int USERID_LENGTH = 8;//userid长度(userid仅仅由数字组成)

    @Autowired
    private UserService userService;//这个可以用static吗？

    //生成userid
    public long genUserid(){
        long res = 0l;

        while(true){
            res = Long.parseLong(RandomString.getNumString(USERID_LENGTH));
            if(checkValid(res) == true){
                break;
            }
        }

        return res;
    }

    //判断该id在数据库中是否已存在
    //userService.findByUserid()
    public boolean checkValid(long userid){

        boolean valid = true;

        UserDTO userDTO = userService.findUserDTOByUserid(userid);

        if(userDTO != null){//todo: 是不是存在的用户这里不为null,不存在的用户这里就是null?? 待验证
            valid = false;
        }

        return valid;

    }

    //测试
    //todo:这里可以学习引入junit或者其他什么来做单元测试？学习一下单元测试怎么做？
    public static void main(String args[]){

    }
}
