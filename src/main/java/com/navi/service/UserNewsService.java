package com.navi.service;

import com.navi.dto.NewsDTO;
import com.navi.dto.UserNewsCommentDTO;
import com.navi.dto.UserNewsFollowDTO;
import com.navi.repository.NewsRepository;
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

    @Autowired
    private NewsRepository newsRepository;


    public UserNewsFollowDTO follow(UserNewsFollowDTO userNewsFollowDTO){
        //1.编辑user-news表
        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsFollowRepository.save(userNewsFollowDTO);

        //2.编辑news表
        //version1: 存在的问题：无法解决同一个用户多次点赞同一个新闻的问题
//        NewsDTO newsDTO = newsRepository.findByDocid(userNewsFollowDTO.getNewsid());
//        newsDTO.setFollows(newsDTO.getFollows()+1);
//        newsRepository.save(newsDTO);

        //version2:
        NewsDTO newsDTO = newsRepository.findByDocid(userNewsFollowDTO.getNewsid());
        int countFollow = userNewsFollowRepository.countAllByNewsidAndFollow(userNewsFollowDTO.getNewsid(), 1);
        //todo: 当用户访问的newsid不存在的话，这里就会报空指针异常。。。作为一个合格的系统这些都要进行处理的
        newsDTO.setFollows(countFollow);
        newsRepository.save(newsDTO);


        //todo: 如何把1，2两个步骤封装成一个事务，换句话的意思是说需要保障他们的原子性

        return userNewsFollowDTOSaved;
    }

    public UserNewsFollowDTO unFollow(UserNewsFollowDTO userNewsFollowDTO){

        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsFollowRepository.save(userNewsFollowDTO);

        NewsDTO newsDTO = newsRepository.findByDocid(userNewsFollowDTO.getNewsid());
        int countFollow = userNewsFollowRepository.countAllByNewsidAndFollow(userNewsFollowDTO.getNewsid(), -1);
        newsDTO.setUnfollows(countFollow);
        newsRepository.save(newsDTO);

        return userNewsFollowDTOSaved;
    }

    public int countNews(String newsid, int follow){

        int count = userNewsFollowRepository.countAllByNewsidAndFollow(newsid, follow);

        return count;
    }

    public UserNewsCommentDTO addComment(UserNewsCommentDTO userNewsCommentDTO){

        UserNewsCommentDTO userNewsCommentDTOSaved = userNewsCommentRepository.save(userNewsCommentDTO);

        return userNewsCommentDTOSaved;
    }


}
