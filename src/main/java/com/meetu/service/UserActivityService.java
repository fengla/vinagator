package com.meetu.service;

import com.meetu.dto.CommentDTO;
import com.meetu.dto.UserActivityDTO;
import com.meetu.repository.CommentRepository;
import com.meetu.repository.UserActivityRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 说明：UserActivityDTO数据表相关的业务操作
 */

@Log4j
public class UserActivityService {

    @Autowired
    private UserActivityRepository userActivityRepository;

//    public List<UserActivityDTO> findUserActivityDTOByActivityId(Long activityId){
//        return userActivityRepository.findUserActivityDTOByActivityId(activityId);
//    }

    public int countUserActivityDTOSByActivityId(Long activityId){
        return userActivityRepository.countUserActivityDTOSByActivityId(activityId);
    }

    public UserActivityDTO save(UserActivityDTO userActivityDTO){
        return userActivityRepository.save(userActivityDTO);
    }
}
