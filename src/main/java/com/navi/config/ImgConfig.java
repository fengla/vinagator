package com.navi.config;

import com.navi.service.PreviewService;
import com.navi.util.ImgUtil;
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
