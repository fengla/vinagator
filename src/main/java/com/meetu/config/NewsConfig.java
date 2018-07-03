package com.meetu.config;

import com.meetu.service.CtService;
import com.meetu.service.NewsService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class NewsConfig {

    @Bean
    public NewsService getNewsService(){
        return new NewsService();
    }
}
