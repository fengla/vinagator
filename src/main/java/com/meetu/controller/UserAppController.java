package com.meetu.controller;

import com.meetu.dto.UserAppFollowDTO;
import com.meetu.service.UserAppService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
public class UserAppController {

    @Autowired
    private UserAppService userAppService;

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

    @GetMapping("/addFollow")
    public Object register(Long userid, Long appid){

        long ts = System.currentTimeMillis()/1000;
        UserAppFollowDTO userAppFollowDTO = new UserAppFollowDTO(userid, appid, ts);
        UserAppFollowDTO userAppFollowDTOSaved = userAppService.follow(userAppFollowDTO);


        return userAppFollowDTOSaved;
    }


}
