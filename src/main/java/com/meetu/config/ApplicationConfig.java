package com.meetu.config;

import java.util.ArrayList;
import java.util.List;

import com.meetu.util.JsonReturnHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 描述：
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年10月20日      Simba.Hua         Create
 * ****************************************************************************
 * </pre>
 * @author Simba.Hua
 */
@Configuration
//@ComponentScan(basePackages = {"com.meetu.util"},useDefaultFilters = true)
@ComponentScan(useDefaultFilters = true)
//@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter{
    @Bean
    public JsonReturnHandler jsonReturnHandler(){
        return new JsonReturnHandler();//初始化json过滤器
    }
    @Override
    public void addReturnValueHandlers(
            List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        returnValueHandlers.add(jsonReturnHandler());

    }
}

//@SpringBootApplication
//@Import(value = {ApplicationConfig.class})
//public class AutomaticSqlApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(AutomaticSqlApplication.class, args);
//    }
//}
