package com.navi.repository;

import com.navi.dto.FeedbackDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface FeedbackRepository extends CrudRepository<FeedbackDTO, Long>{


    public FeedbackDTO save(FeedbackDTO feedbackDTO);

    //改为分页查询。。。管理后台提供一个管理回复的功能页面。。。可以用户编辑逐条回复用户
    //查询所有
    //public List<FeedbackDTO> findAll();
    public Page<FeedbackDTO> findAll(Pageable pageable);

    public FeedbackDTO findById(long id);//基于feedback自身的id查询

    public Page<FeedbackDTO> findByUserid(long userid, Pageable pageable);//基于用户的id查询


}
