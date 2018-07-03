package com.meetu.controller;


import com.meetu.dto.BannerDTO;
import com.meetu.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class BannerController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private BannerService bannerService;


    /**
     * 查询Banners
     */
    @GetMapping("/getBanners")
    public Object getBanners(){

        List<BannerDTO> banners = new ArrayList<>();
        banners = bannerService.findAll();

        log.warn(banners.toString());


        return banners;
    }



}
