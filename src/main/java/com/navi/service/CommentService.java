package com.navi.service;

import com.navi.dto.CommentDTO;
import com.navi.repository.CommentRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentDTO> findCommentDTOByActivityId(Long activityId){
        return commentRepository.findCommentDTOByActivityId(activityId);
    }

    public CommentDTO save(CommentDTO commentDTO){
        return commentRepository.save(commentDTO);
    }
}
