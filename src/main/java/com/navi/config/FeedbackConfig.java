package com.navi.config;

import com.navi.service.FeedbackService;
import com.navi.util.FBUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class FeedbackConfig {

    @Bean
    public FeedbackService getFeedbackService(){
        return new FeedbackService();
    }

    @Bean
    public FBUtil getFBUtil(){
        return new FBUtil();
    }
}
