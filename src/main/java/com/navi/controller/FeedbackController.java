package com.navi.controller;


import com.navi.dto.FeedbackDTO;
import com.navi.service.FeedbackService;
import com.navi.util.FBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
//import javax.servlet.ServletContext;

@Slf4j
@RestController
public class FeedbackController {

//    @Autowired
//    ServletContext context;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FBUtil fbUtil;

    /**
     * feedback
     */
    @PostMapping("/putFeedback")
    public Object putFeedback(@Param("userid") long userid,@Param("feedback") String feedback){

        log.info("enter getAllFeedbacks");

        long fbid = fbUtil.genFBid();
        FeedbackDTO feedbackDTO = new FeedbackDTO(fbid, userid, feedback);
        feedbackService.save(feedbackDTO);

        return feedbackDTO;
    }


    /**
     * 查询所有feedback
     */
    @GetMapping("/getAllFeedbacks")
    public Object getAllFeedbacks(int curPage){

        log.info("enter getAllFeedbacks");

        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        Page<FeedbackDTO> feedbackDTOPage = feedbackService.findAll(curPage, direction, sortProperty);

        return feedbackDTOPage;
    }

    /**
     * 查询指定用户所有的反馈信息
     * @param curPage
     * @param userid
     * @return
     */
    @GetMapping("/getFeedbackByUser")
    public Object getFeedbackByUser(int curPage, long userid){

        log.info("enter getFeedbackByUser, userid:" + userid);

        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        Page<FeedbackDTO> feedbackDTOPage = feedbackService.findByUserid(userid, curPage, direction, sortProperty);

        return feedbackDTOPage;
    }

    /**
     * 基于id查询指定的反馈信息
     * @param fbid
     * @return
     */
    @GetMapping("/getFeedbackById")
    public Object getFeedbackById(long fbid){

        log.info("enter getFeedbackByUser, fbid:" + fbid);

        FeedbackDTO feedbackDTO = feedbackService.findById(fbid);

        return feedbackDTO;
    }



}
