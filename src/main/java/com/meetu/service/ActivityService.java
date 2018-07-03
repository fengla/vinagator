package com.meetu.service;

import com.meetu.dto.ActivityDTO;
import com.meetu.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void save(ActivityDTO activityDTO){
        activityRepository.save(activityDTO);
    }

    public List<ActivityDTO> findAll(){
        return activityRepository.findAll();
    }

    public ActivityDTO findById(Long activityId){
        return activityRepository.findById(activityId);
    }
}
