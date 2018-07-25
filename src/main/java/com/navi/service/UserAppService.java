package com.navi.service;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import com.navi.repository.UserAppFollowRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Log4j
public class UserAppService {

    @Autowired
    private UserAppFollowRepository userAppFollowRepository;


    public UserAppFollowDTO follow(UserAppFollowDTO userAppFollowDTO){

        UserAppFollowDTO userAppFollowDTOSaved = userAppFollowRepository.save(userAppFollowDTO);

        return userAppFollowDTOSaved;
    }

    public Page<AppDTO> findFollowedApps(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return userAppFollowRepository.findFollowedApps(pageable);
    }

    public Page<AppDTO> findFollowedApps(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return userAppFollowRepository.(pageable);
    }


}
