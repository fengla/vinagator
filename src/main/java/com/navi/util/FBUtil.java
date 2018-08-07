package com.navi.util;

import com.navi.dto.FeedbackDTO;
import com.navi.dto.UserDTO;
import com.navi.service.FeedbackService;
import com.navi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * feedback
 */
public class FBUtil {

    private static final int FB_LENGTH = 6;

    @Autowired
    private FeedbackService feedbackService;

    //生成userid
    public long genFBid(){
        long res = 0l;

        while(true){
            res = Long.parseLong(RandomString.getNumString(FB_LENGTH));
            if(checkValid(res) == true){
                break;
            }
        }

        return res;
    }

    //判断该id在数据库中是否已存在
    public boolean checkValid(long fbid){

        boolean valid = true;

        FeedbackDTO feedbackDTO = feedbackService.findById(fbid);

        if(feedbackDTO != null){
            valid = false;
        }

        return valid;

    }

    public static void main(String args[]){

    }
}
