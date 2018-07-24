package com.navi.service;

import com.navi.dto.UserAppFollowDTO;
import com.navi.repository.UserAppFollowRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class UserAppService {

    @Autowired
    private UserAppFollowRepository userAppFollowRepository;


    public UserAppFollowDTO follow(UserAppFollowDTO userAppFollowDTO){

        UserAppFollowDTO userAppFollowDTOSaved = userAppFollowRepository.save(userAppFollowDTO);

        return userAppFollowDTOSaved;
    }


}
