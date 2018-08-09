package com.navi.controller;

import com.navi.dto.NewsDTO;
import com.navi.dto.UserNewsFollowDTO;
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
    public Object getNewsByPage(int curPage, long userid){

        log.warn("enter showAppsByCT, curPage:" + curPage);

        Page<NewsDTO> news = null;

        //todo: 现在的冷启动策略就展示每个类别的最新更新的app吧。后续再调整排序策略（权重进行，综合：更新时间，关注人数，编辑评分等）

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "ts";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        news = newsService.findNews(curPage, direction, sortProperty);
        List<String> newsids = new ArrayList<>();
        for(NewsDTO cur : news){//经验证，Page类型也可以这样进行遍历
            newsids.add(cur.getDocid());
            if(cur.getImg()!=null && !"".equals(cur.getImg()) && !"null".equals(cur.getImg())){
                cur.setContainsImg(true);
            }//todo:不需要else? 因为boolean默认的值就是false?

            //再设置当前用户是不是已经点赞／踩了这个news，对于已经操作过的设置follow,unfollow字段为true
        }

        //jpa mysql批量查询
        List<UserNewsFollowDTO> userNewsFollowDTOS = userNewsService.findByUseridAndNewsid(userid, newsids);
        System.out.println("[debug-follows-relations]:" + userNewsFollowDTOS);

        //设置关注与否的boolean值
        for(NewsDTO cur : news){
            for(UserNewsFollowDTO userNewsFollowDTO : userNewsFollowDTOS){
                if(userNewsFollowDTO.getNewsid().equals(cur.getDocid())){
                    if(userNewsFollowDTO.getFollow()==1){//zan
                        cur.setFollow(true);
                    }else if(userNewsFollowDTO.getFollow()==-1){//cai
                        cur.setUnfollow(true);
                    }else{
                        log.error("wrong follow relation, userid:" + userid + ", docid:" + cur.getDocid());
                    }
                }
            }
        }

        log.warn(news.toString());
        //todo:【优化】这里其实就是2个表的join查询。。后期优化可以改成join查询，当前这么些相当于把业务压力从数据库转移到了server

        return news;
    }



}
