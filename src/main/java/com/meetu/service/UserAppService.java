package com.meetu.service;

import com.meetu.dto.UserAppFollowDTO;
import com.meetu.dto.UserDTO;
import com.meetu.repository.UserAppFollowRepository;
import com.meetu.repository.UserRepository;
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
