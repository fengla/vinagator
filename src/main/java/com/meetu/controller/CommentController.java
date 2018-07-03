package com.meetu.controller;

import com.meetu.data.CommentDetail;
import com.meetu.data.User;
import com.meetu.dto.CommentDTO;
import com.meetu.dto.UserDTO;
import com.meetu.service.CommentService;
import com.meetu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/showComments")
    public String profile(ModelMap modelMap, Long activityId){//ModelMap modelMap

        return null;
    }


    @PostMapping("/putComment")
    public CommentDetail putComment(ModelMap modelMap, Long userId, Long activityId, String content){
        log.warn("userId:" + userId + "|| activityId:" + activityId + "|| content:" + content);
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date updateTime = new Date();//获取当前系统时间
        //处理换行
        content = content.replaceAll("\r\n","<br>");

        //保存数据库
        CommentDTO commentDTO = new CommentDTO(content, activityId, userId, updateTime);
        commentService.save(commentDTO);

        //反馈前端页面
        CommentDetail commentDetail = new CommentDetail();
        UserDTO userDTO = userService.findUserDTOById(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        commentDetail.setUserName(userDTO.getNickName());
        commentDetail.setUserLogo(userDTO.getPortrait());
        commentDetail.setContent(content);
        commentDetail.setUpdateTime(df.format(updateTime));

        //todo：这种先查完整的CommentDTO再在业务逻辑中封装到CommentDetail完全没必要，可以在repository那里自定义查询，这样一次搞定，也减少了数据传来传去的量；

        return commentDetail;
    }


}
