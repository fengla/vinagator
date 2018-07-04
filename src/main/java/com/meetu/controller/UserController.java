package com.meetu.controller;

import com.meetu.data.UserEase;
import com.meetu.dto.UserDTO;
import com.meetu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.ModelMap;

@Log4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询指定用户在本平台关注过的所有app
     */

    @GetMapping("/profile")
    public String profile(ModelMap modelMap, UserEase userEase){//ModelMap modelMap

        log.warn("enter profile, user:" + userEase);
        UserDTO userDTO = userService.findUserDTOById(userEase.getId());
        modelMap.addAttribute("user", userDTO);

        return "profile";
    }


}
