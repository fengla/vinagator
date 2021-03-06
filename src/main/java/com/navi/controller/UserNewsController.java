package com.navi.controller;

import com.navi.data.response.CommonResp;
import com.navi.dto.UserNewsCommentDTO;
import com.navi.dto.UserNewsFollowDTO;
import com.navi.service.UserNewsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户与新闻之间的关系：评论，点赞等
 */

@Log4j
@RestController
public class UserNewsController {

    @Autowired
    private UserNewsService userNewsService;

    @GetMapping("/followNews")
    public Object followNews(Long userid, String newsid){

        log.warn("followNews, userid:"+userid+" newsid:" + newsid);

        long ts = System.currentTimeMillis()/1000;
        UserNewsFollowDTO userNewsFollowDTO = new UserNewsFollowDTO(userid, newsid, 1, ts);
        CommonResp commonResp = userNewsService.follow(userNewsFollowDTO);
        //这里不要直接返回DTO对象，而是应该返回成功或者失败的信息；毕竟需要用户知道他已经点赞过了还是这一次成功点赞了。
        //return success or failed(why?悬浮模态框提示：你已经点过赞了哟！)

        return commonResp;
    }
    //todo: 一个用户是否可以多次点赞同一个文章，如何做到一个用户只能点击一次呢？实现查询展示到前台的时候就查询出该信息，然后设置前端样式？
    //如果一个用户可以多次点击。。那么一个用户可以无限创建数据库连接，就可以打挂掉我的服务。。
    //todo: important 那么涉及到整个系统的问题也来了：如何限制统一用户的过于频繁的请求？

    @GetMapping("/unFollowNews")
    public Object unFollowNews(Long userid, String newsid){

        log.warn("unFollowNews, userid:"+userid+" newsid:" + newsid);

        long ts = System.currentTimeMillis()/1000;
        UserNewsFollowDTO userNewsFollowDTO = new UserNewsFollowDTO(userid, newsid, -1, ts);
        CommonResp commonResp = userNewsService.unFollow(userNewsFollowDTO);
        //这里不要直接返回DTO对象，而是应该返回成功或者失败的信息；毕竟需要用户知道他已经点赞过了还是这一次成功点赞了。
        //return success or failed(why?悬浮模态框提示：你已经点过赞了哟！)

        return commonResp;
    }

    @GetMapping("/getNewsFollows")
    public Object getNewsFollows(String newsid){
        //统计新闻的点赞点踩信息等
        log.warn(String.format("countNews newsid:%s", newsid));

        int countFollow = userNewsService.countNews(newsid, 1);//赞
        int countUnFollow = userNewsService.countNews(newsid, -1);//赞
        int countDefault = userNewsService.countNews(newsid, 0);//赞
        Map<String,Integer> counter = new HashMap<>();
        counter.put("赞", countFollow);
        counter.put("踩", countUnFollow);
        counter.put("默认", countDefault);

        return counter;
    }

    //todo:评论应该还挺复杂的，暂且先把评论功能在前端隐藏掉，后续开发完成后再放出来
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
