package com.meetu.controller;

import com.meetu.dto.AppDTO;
import com.meetu.dto.NewsDTO;
import com.meetu.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class NewsController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？todo:没有用的话可以删除掉吗？

    @Autowired
    private NewsService newsService;

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



}
