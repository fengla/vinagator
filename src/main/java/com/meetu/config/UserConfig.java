package com.meetu.config;

import com.meetu.service.UserService;
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
}
