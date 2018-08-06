package com.navi.controller;


import com.navi.service.CtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;

@Slf4j
@RestController
public class FeedbackController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private CtService ctService;


    /**
     * 查询cts
     */
    @GetMapping("/getAllFeedbacks")
    public Object getAllFeedbacks(int curpage){




        return null;
    }

    //todo: 基于用户id查询（分页查询）

    //todo: 基于feedbackid查询（只查询出来单条）



}
