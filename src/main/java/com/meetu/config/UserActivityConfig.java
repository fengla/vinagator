package com.meetu.config;

import com.meetu.service.UserActivityService;
import com.meetu.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class UserActivityConfig {

    @Bean
    public UserActivityService getUserActivityService(){
        return new UserActivityService();
    }
}
