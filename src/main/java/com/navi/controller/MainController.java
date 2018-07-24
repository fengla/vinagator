package com.navi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;


//todo: 这个是什么时候的代码？是不是可以删除了？？？？？


@Slf4j
@RestController
public class MainController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    /**
     * 查询【所有】一级分类（即左边栏）
     */
    @GetMapping("/showCts")
    //@ResponseBody
    public Object showCts(){
        Map<Long, String> cts = new HashMap<>();
        cts.put(1l, "影音");
        cts.put(2l,"导航");
        cts.put(3l,"社交");
        cts.put(4l,"直播");
        cts.put(5l,"工具");//todo:先这么维护一个静态的后续还是可以写到数据库中

        return cts;
    }

    /**
     * 查询指定一级分类下的所有二级分类（目前构思仅仅针对游戏分类才有二级分类）
     */
    @GetMapping("/showScts")
    //@ResponseBody
    public Object showScts(@Param("ct") String ct){

        //todo: debug
        System.out.println("ct:" + ct);

        Map<Long, String> scts = new HashMap<>();
        scts.put(1l,"棋牌");
        scts.put(2l,"策略");
        scts.put(3l,"竞速");
        scts.put(4l,"休闲");

        return scts;
    }




}
