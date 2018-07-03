package com.meetu.repository;

import com.meetu.dto.ActivityDTO;
import com.meetu.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ActivityRepository extends CrudRepository<ActivityDTO, Long>{


    public ActivityDTO save(ActivityDTO activityDTO);//添加活动

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    public List<ActivityDTO> findAll();//查询所有活动

    public List<ActivityDTO> findAllByDateAfter(String date);//查询当前日期之后的所有活动

    public ActivityDTO findById(Long id);
}
