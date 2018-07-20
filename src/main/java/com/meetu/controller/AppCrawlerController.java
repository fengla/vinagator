package com.meetu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.meetu.dto.AppDTO;
import com.meetu.dto.CtDTO;
import com.meetu.dto.PreviewDTO;
import com.meetu.service.AppCrawlerService;
import com.meetu.service.AppService;
import com.meetu.service.CtService;
import com.meetu.service.PreviewService;
import com.meetu.util.AppUtil;
import com.meetu.util.ImgUtil;
import com.meetu.util.JsonSerializer;
import com.yidian.push.lib.core.util.HttpConnectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Controller
public class AppCrawlerController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private AppCrawlerService appCrawlerService;

    @Autowired
    private AppService appService;

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ImgUtil imgUtil;

    @Autowired
    private PreviewService previewService;


    @GetMapping("/crawler")
    @ResponseBody
    public Object launchActivity(String url){

        log.warn("srcUrl:" + url);

        try {
            Map<String, Object> params = new HashMap<String, Object>();//目前对于damengxiang.me这个网站的请求，这个参数没有用处
            String response = HttpConnectionUtils.getGetResult( url, params);

            JSONObject appJson = appCrawlerService.parseFromResponse(response);

            AppDTO appDTO = appCrawlerService.encapAppDTO(appJson);

            //appCrawlerService.save(appDTO);

            return appDTO;
        }catch(Exception e){
            log.error("crawler app failed", e);
        }
        return "crawl app failed";
    }


}
