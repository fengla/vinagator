package com.meetu.config;

import com.meetu.service.CommentService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class CommentConfig {

    @Bean
    public CommentService getCommentService(){
        return new CommentService();
    }
}
