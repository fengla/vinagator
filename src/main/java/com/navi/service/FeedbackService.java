package com.navi.service;

import com.navi.dto.FeedbackDTO;
import com.navi.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class FeedbackService {

    private final int FB_SIZE_PAGE = 8;

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void save(FeedbackDTO ctDTO){
        feedbackRepository.save(ctDTO);
    }

    public Page<FeedbackDTO> findAll(int curPage, Sort.Direction direction, String sortProperty){
        Sort sort = new Sort(direction, sortProperty);
        Pageable pageable = new PageRequest(curPage, FB_SIZE_PAGE, sort);
        return feedbackRepository.findAll(pageable);
    }

    public FeedbackDTO findById(Long fbid){
        return feedbackRepository.findById(fbid);
    }

    public Page<FeedbackDTO> findByUserid(Long userid, int curPage, Sort.Direction direction, String sortProperty){
        Sort sort = new Sort(direction, sortProperty);
        Pageable pageable = new PageRequest(curPage, FB_SIZE_PAGE, sort);
        return feedbackRepository.findByUserid(userid, pageable);
    }


}
