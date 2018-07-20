package com.meetu.config;

import com.meetu.service.AppService;
import com.meetu.util.AppUtil;
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

    @Bean
    public AppUtil getAppUtil(){
        return new AppUtil();
    }
}
