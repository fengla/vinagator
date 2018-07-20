package com.meetu.config;

import com.meetu.service.AppService;
import com.meetu.service.PreviewService;
import com.meetu.util.AppUtil;
import com.meetu.util.ImgUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j
@Configuration
public class ImgConfig {

    @Bean
    public ImgUtil getImgUtil(){
        return new ImgUtil();
    }

    @Bean
    public PreviewService getPreviewService(){
        return new PreviewService();
    }
}
