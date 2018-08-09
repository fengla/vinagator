package com.navi.service;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import com.navi.repository.UserAppFollowRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;

@Slf4j
@Transactional
public class UserAppService {

    private final int PAGESIZE = 10;

    @Autowired
    private UserAppFollowRepository userAppFollowRepository;


    public UserAppFollowDTO follow(UserAppFollowDTO userAppFollowDTO){
        log.info("enter followApp, userAppFollowDTP:" + userAppFollowDTO);
        UserAppFollowDTO userAppFollowDTOSaved = userAppFollowRepository.save(userAppFollowDTO);

        return userAppFollowDTOSaved;
    }

    public boolean unfollow(long userid, long appid, long updateDate){
        boolean res = true;
        try {
            userAppFollowRepository.unfollow(userid, appid, false, updateDate);//应该是update
        }catch(Exception e){
            res = false;
            log.error("unfollow failed, details:", e);
        }

        return res;
    }

    public boolean checkFollowed(long userid, long appid){
        boolean isFollowed = true;

        UserAppFollowDTO userAppFollowDTOSaved = userAppFollowRepository.findByUseridAndAppid(userid, appid);
        if(userAppFollowDTOSaved==null || !userAppFollowDTOSaved.isFollow()){
            isFollowed = false;
        }

        return isFollowed;
    }

    public int countFollowedApps(long userid){

        //只做count，不需要查询所有app数据（其实现在的Pagable查询的时候也会展示总共的记录数啊，所以这里其实没必要有这个方法的存在）

        return 0;
    }

    public int findFollowedAppsCount(long userid){

        return userAppFollowRepository.countAllByUserid(userid);
    }

    public Page<AppDTO> findFollowedApps(long userid, int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, PAGESIZE, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return userAppFollowRepository.findFollowedApps(userid, pageable);
    }

    public Page<AppDTO> findFollowedAppsByCT(long userid, long ctid, int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return userAppFollowRepository.findFollowedAppsByCT(userid, ctid, pageable);
    }


}
