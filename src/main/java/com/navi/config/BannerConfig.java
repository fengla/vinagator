package com.navi.config;

import com.navi.service.BannerService;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class BannerConfig {

    @Bean
    public BannerService getBannerService(){
        return new BannerService();
    }
}
