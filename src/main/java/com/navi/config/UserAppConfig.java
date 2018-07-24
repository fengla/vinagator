package com.navi.config;

import com.navi.service.UserAppService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class UserAppConfig {

    @Bean
    public UserAppService getUserAppFollowService(){
        return new UserAppService();
    }
}
