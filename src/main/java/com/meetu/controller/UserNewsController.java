package com.meetu.controller;

import com.meetu.dto.UserNewsCommentDTO;
import com.meetu.dto.UserNewsFollowDTO;
import com.meetu.service.UserNewsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户与新闻之间的关系：评论，点赞等
 */

@Log4j
@RestController
public class UserNewsController {

    @Autowired
    private UserNewsService userNewsService;

    @GetMapping("/followNews")
    public Object followNews(Long userid, Long newsid, int follow){

        log.warn(String.format("follow news, userid:%f, newsid:%f", userid, newsid));

        long ts = System.currentTimeMillis()/1000;
        UserNewsFollowDTO userNewsFollowDTO = new UserNewsFollowDTO(userid, newsid, follow, ts);
        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsService.follow(userNewsFollowDTO);
        //这里不要直接返回DTO对象，而是应该返回成功或者失败的信息；毕竟需要用户知道他已经点赞过了还是这一次成功点赞了。
        //return success or failed(why?悬浮模态框提示：你已经点过赞了哟！)

        return userNewsFollowDTOSaved;
    }


    //小程序前端如何发起post请求？
    //前端判断新评论是回复评论的还是回复新闻本身的，然后针对的传id回来后端
    //todo: 第一版本只做对新闻的评论，不做对评论的评论吧
    @PostMapping("/commentNews")
    public Object commentNews(Long userid, Long newsid, Long cid_reply, String content){

        log.warn(String.format("comment news, userid:%d, newsid:%d", userid, newsid));//long类型应该用%d

        long ts = System.currentTimeMillis()/1000;
        UserNewsCommentDTO userNewsCommentDTO = new UserNewsCommentDTO(userid, newsid, cid_reply, content, ts);
        UserNewsCommentDTO userNewsCommentDTOSaved = userNewsService.addComment(userNewsCommentDTO);

        return userNewsCommentDTOSaved;
    }


}
