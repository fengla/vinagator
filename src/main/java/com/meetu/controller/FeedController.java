package com.meetu.controller;

import com.meetu.data.UserEase;
import com.meetu.dto.UserDTO;
import com.meetu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j
@Controller
public class FeedController {

    @Autowired
    private UserService userService;

    /**
     * 信息流（目前只开通搞笑频道。。内涵段子）
     */

    @GetMapping("/showFeeds")
    public String profile(ModelMap modelMap, UserEase userEase){//ModelMap modelMap

        log.warn("enter profile, user:" + userEase);
        UserDTO userDTO = userService.findUserDTOById(userEase.getId());
        modelMap.addAttribute("user", userDTO);

        return "profile";
    }


}
