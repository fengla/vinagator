package com.meetu.controller;

import com.meetu.dto.NewsDTO;
import com.meetu.dto.UserNewsCommentDTO;
import com.meetu.dto.UserNewsFollowDTO;
import com.meetu.service.NewsService;
import com.meetu.service.UserNewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;


/**
 * 信息流（目前只开通搞笑频道。。内涵段子）
 */

@Slf4j
@RestController
public class NewsController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？todo:没有用的话可以删除掉吗？

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserNewsService userNewsService;

    /**
     * 查询news
     */
    @GetMapping("/getAllNews")
    public Object getAllNews(String cur, int limit){
        log.warn(String.format("request allNews, cur:%s, limit:%d", cur, limit));

        List<NewsDTO> newsList = new ArrayList<>();
        newsList = newsService.findAll();

        //todo:怎么分页查询，分页返回呢？我们这里还是需要设计一些更为复杂的查询，不该这样直接查询所有数据出来，浪费查询资源
        log.warn(newsList.toString());


        return newsList;
    }

    @GetMapping("/getNewsByPage")
    public Object getNewsByPage(int curPage){
        //todo:
        log.warn("enter showAppsByCT, curPage:" + curPage);

        Page<NewsDTO> news = null;

        //todo: 现在的冷启动策略就展示每个类别的最新更新的app吧。后续再调整排序策略（权重进行，综合：更新时间，关注人数，编辑评分等）

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "ts";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        news = newsService.findNews(curPage, direction, sortProperty);

        log.warn(news.toString());

        return news;
    }


    @GetMapping("/followNews")
    public Object followNews(Long userid, Long newsid, int follow){

        log.warn(String.format("follow news, userid:%f, newsid:%f", userid, newsid));

        long ts = System.currentTimeMillis()/1000;
        UserNewsFollowDTO userNewsFollowDTO = new UserNewsFollowDTO(userid, newsid, follow, ts);
        UserNewsFollowDTO userNewsFollowDTOSaved = userNewsService.follow(userNewsFollowDTO);

        return userNewsFollowDTOSaved;
    }


    //小程序前端如何发起post请求？
    //前端判断新评论是回复评论的还是回复新闻本身的，然后针对的传id回来后端
    //todo: 第一版本只做对新闻的评论，不做对评论的评论吧
    @PostMapping("/commentNews")
    public Object commentNews(Long userid, Long newsid, Long cid_reply, String content){

        log.warn(String.format("comment news, userid:%f, newsid:%f", userid, newsid));

        long ts = System.currentTimeMillis()/1000;
        UserNewsCommentDTO userNewsCommentDTO = new UserNewsCommentDTO(userid, newsid, cid_reply, content, ts);
        UserNewsCommentDTO userNewsCommentDTOSaved = userNewsService.addComment(userNewsCommentDTO);

        return userNewsCommentDTOSaved;
    }



}
