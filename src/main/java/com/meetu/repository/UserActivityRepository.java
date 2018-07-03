package com.meetu.repository;

import com.meetu.dto.ActivityDTO;
import com.meetu.dto.UserActivityDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserActivityRepository extends CrudRepository<UserActivityDTO, Long>{


    public UserActivityDTO save(UserActivityDTO userActivityDTO);//添加活动

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。
    public List<UserActivityDTO> findAllByActivityId(Long activityId);
    public int countUserActivityDTOSByActivityId(Long activityId);//todo: 还有一个countUserActivityDTO[[[S]]]ByActivityId;这2个方法有什么区别？

    public ActivityDTO findById(Long id);
}
