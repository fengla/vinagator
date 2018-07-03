package com.meetu.config;

import com.meetu.service.ActivityService;
import com.meetu.service.AppService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class AppConfig {

    @Bean
    public AppService getAppService(){
        return new AppService();
    }
}
