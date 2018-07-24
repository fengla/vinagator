package com.navi;

//import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.navi.*")
public class Application {
    public static void main(String args[]){
        SpringApplication.run(Application.class);
    }
}

