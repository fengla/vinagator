package com.meetu.controller;

import com.meetu.data.UserEase;
import com.meetu.dto.CtDTO;
import com.meetu.dto.UserDTO;
import com.meetu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

@Log4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询指定用户在本平台关注过的所有app
     */

//    @GetMapping("/profile")
//    public String profile(ModelMap modelMap, UserEase userEase){//ModelMap modelMap
//
//        log.warn("enter profile, user:" + userEase);
//        UserDTO userDTO = userService.findUserDTOByUserid(userEase.getId());
//        modelMap.addAttribute("user", userDTO);
//
//        return "profile";
//    }

    @GetMapping("/register")
    public Object register(Long userid, String wechat, String nickName){


        UserDTO userDTONew = new UserDTO(userid, wechat, nickName);

        UserDTO userDTOSaved = userService.register(userDTONew);


        return userDTOSaved;
    }

    /**
     * 获取指定用户所有关注的小程序
     * @param userid
     * @return
     */
    @GetMapping("/getAllFollowedApps")
    public Object getAllFollowedApps(Long userid){


        //先从UserAppFollow关系表中获取所有该用户关注的appid


        //再基于第一步的appid从appdto表中获取应用详情（属于哪个分类等）。。展示某分类下该用户关注的app信息，可以使用多表join查询（JPA join查询的实现）


        return null;
    }


}
