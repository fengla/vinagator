package com.meetu.config;

import com.meetu.service.UserNewsService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class UserNewsConfig {

    @Bean
    public UserNewsService getUserNewsService(){
        return new UserNewsService();
    }
}
