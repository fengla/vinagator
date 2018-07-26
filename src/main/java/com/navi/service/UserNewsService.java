package com.navi.service;

import com.navi.dto.UserNewsCommentDTO;
import com.navi.dto.UserNewsFollowDTO;
import com.navi.repository.UserNewsCommentRepository;
import com.navi.repository.UserNewsFollowRepository;
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

    public UserNewsFollowDTO unFollow(UserNewsFollowDTO userNewsFollowDTO){

        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsFollowRepository.save(userNewsFollowDTO);

        return userNewsFollowDTOSaved;
    }

    public UserNewsCommentDTO addComment(UserNewsCommentDTO userNewsCommentDTO){

        UserNewsCommentDTO userNewsCommentDTOSaved = userNewsCommentRepository.save(userNewsCommentDTO);

        return userNewsCommentDTOSaved;
    }


}
