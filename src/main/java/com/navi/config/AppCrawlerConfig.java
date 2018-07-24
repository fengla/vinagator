package com.navi.config;

import com.navi.service.AppCrawlerService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class AppCrawlerConfig {

    @Bean
    public AppCrawlerService getAppCrawlerService(){
        return new AppCrawlerService();
    }

}
