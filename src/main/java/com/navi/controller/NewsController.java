package com.navi.controller;

import com.navi.dto.NewsDTO;
import com.navi.service.NewsService;
import com.navi.service.UserNewsService;
import com.navi.util.JsonFieldFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;


/**
 * 信息流（目前只开通搞笑频道。。内涵段子）
 */

@Slf4j
@Controller
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
    @ResponseBody
    public Object getAllNews(String cur, int limit){
        log.warn(String.format("request allNews, cur:%s, limit:%d", cur, limit));

        List<NewsDTO> newsList = new ArrayList<>();
        newsList = newsService.findAll();

        //todo:怎么分页查询，分页返回呢？我们这里还是需要设计一些更为复杂的查询，不该这样直接查询所有数据出来，浪费查询资源
        log.warn(newsList.toString());


        return newsList;
    }

    @GetMapping("/getNewsDetail")
    @ResponseBody
    public Object getNewsDetail(String newsid){

        log.warn(String.format("getNewsDetail, newsid:%s", newsid));

        NewsDTO newsDTO = newsService.findById(newsid);

        return newsDTO;
    }

    @GetMapping("/getNewsByPage")
    @JsonFieldFilter(type = NewsDTO.class,exclude = "ts")//把gitPassword字段过滤掉
    public Object getNewsByPage(int curPage){
        //todo:
        log.warn("enter showAppsByCT, curPage:" + curPage);

        Page<NewsDTO> news = null;

        //todo: 现在的冷启动策略就展示每个类别的最新更新的app吧。后续再调整排序策略（权重进行，综合：更新时间，关注人数，编辑评分等）

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "ts";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        news = newsService.findNews(curPage, direction, sortProperty);

        for(NewsDTO cur : news){//todo: news是个Page类型的，可以这样进行遍历吗？
            if(cur.getImg()!=null && !"".equals(cur.getImg()) && !"null".equals(cur.getImg())){
                cur.setContainsImg(true);
            }//todo:不需要else? 因为boolean默认的值就是false?
        }

        log.warn(news.toString());

        return news;
    }



}
