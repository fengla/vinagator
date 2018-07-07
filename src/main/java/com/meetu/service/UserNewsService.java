package com.meetu.service;

import com.meetu.dto.UserNewsCommentDTO;
import com.meetu.dto.UserNewsFollowDTO;
import com.meetu.repository.UserNewsCommentRepository;
import com.meetu.repository.UserNewsFollowRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class UserNewsService {

    @Autowired
    private UserNewsFollowRepository userNewsFollowRepository;

    @Autowired
    private UserNewsCommentRepository userNewsCommentRepository;


    public UserNewsFollowDTO follow(UserNewsFollowDTO userNewsFollowDTO){

        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsFollowRepository.save(userNewsFollowDTO);

        return userNewsFollowDTOSaved;
    }

    public UserNewsCommentDTO addComment(UserNewsCommentDTO userNewsCommentDTO){

        UserNewsCommentDTO userNewsCommentDTOSaved = userNewsCommentRepository.save(userNewsCommentDTO);

        return userNewsCommentDTOSaved;
    }


}
