package com.navi.controller;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import com.navi.service.UserAppService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *用户与app之间的关系：评论，关注等
 */

@Slf4j
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
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = userAppService.findFollowedApps(userid, curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;
    }

    /**
     * 查询指定用户在指定分类下关注过的所有app
     */
    @GetMapping("/findFollowedAppsByCT")
    @ResponseBody
    public Object findFollowedAppsByCT(long userid, long ctid, int curPage){
        log.warn("enter findFollowedAppsByCT, userid:" + userid + "curPage:" + curPage);

        Page<AppDTO> apps = null;

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = userAppService.findFollowedAppsByCT(userid, ctid, curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;
    }

    /**
     * 关注
     * @param userid
     * @param appid
     * @return
     */
    @GetMapping("/followApp")
    @ResponseBody
    public Object followApp(long userid, long appid){

        log.info("enter followApp, userid:"+userid+", appid:"+appid);

        long ts = System.currentTimeMillis()/1000;
        UserAppFollowDTO userAppFollowDTO = new UserAppFollowDTO(userid, appid, true, ts);
        UserAppFollowDTO userAppFollowDTOSaved = userAppService.follow(userAppFollowDTO);


        return userAppFollowDTOSaved;
    }

    /**
     * 取消关注
     * @param userid
     * @param appid
     * @return
     */
    @GetMapping("/unFollowApp")
    @ResponseBody
    public Object unFollowApp(long userid, long appid){

        long ts = System.currentTimeMillis()/1000;
        boolean resp = userAppService.unfollow(userid, appid, ts);

        //todo: 后续这里可以返回状态吗给前端。。
        //方便响应用户

        return resp;
    }

    //todo：当前取消关注只是把字段设置成了false,并没有删除记录。。这样的话，用户再次关注直接用save应该会失败。。。所以应该改成


}
