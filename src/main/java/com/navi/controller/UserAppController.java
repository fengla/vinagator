package com.navi.controller;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import com.navi.service.UserAppService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *用户与app之间的关系：评论，关注等
 */

@Log4j
@RestController
public class UserAppController {

    @Autowired
    private UserAppService userAppService;

    /**
     * 查询指定用户关注过的所有app
     */
    @GetMapping("/findFollowedApps")
    @ResponseBody
    public Object findFollowedApps(long userid, int curPage){

        log.warn("enter findFollowedApps, userid:" + userid + "curPage:" + curPage);

        Page<AppDTO> apps = null;

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "ts";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = userAppService.findFollowedApps(curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;


        return apps;
    }

    /**
     * 查询指定用户在指定分类下关注过的所有app
     */
    @GetMapping("/findFollowedAppByCT")
    @ResponseBody
    public Object findFollowedAppByCT(long userid, long ctid, int curPage){
        log.warn("enter findFollowedAppByCT, userid:" + userid + "curPage:" + curPage);

        Page<AppDTO> apps = null;

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "ts";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = userAppService.findFollowedAppByCT(curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;


        return apps;
    }

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
    public Object addFollow(long userid, long appid){

        long ts = System.currentTimeMillis()/1000;
        UserAppFollowDTO userAppFollowDTO = new UserAppFollowDTO(userid, appid, ts);
        UserAppFollowDTO userAppFollowDTOSaved = userAppService.follow(userAppFollowDTO);


        return userAppFollowDTOSaved;
    }


}
