package com.navi.config;

import com.navi.service.CtService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class CtConfig {

    @Bean
    public CtService getCtService(){
        return new CtService();
    }
}
