package com.navi.config;

import com.navi.service.UserService;
import com.navi.util.UserUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class UserConfig {

    @Bean
    public UserService getUserService(){
        return new UserService();
    }

    @Bean
    public UserUtil getUserUtil(){
        return new UserUtil();
    }
}
