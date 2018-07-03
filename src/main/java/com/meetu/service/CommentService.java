package com.meetu.service;

import com.meetu.dto.CommentDTO;
import com.meetu.dto.UserDTO;
import com.meetu.repository.CommentRepository;
import com.meetu.repository.UserRepository;
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
