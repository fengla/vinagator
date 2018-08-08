package com.navi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.navi.dto.AppDTO;
import com.navi.service.AppCrawlerService;
import com.navi.service.AppService;
import com.navi.service.PreviewService;
import com.navi.util.AppUtil;
import com.navi.util.HttpConnectionUtils;
import com.navi.util.ImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Object crawler(String url){

        log.warn("url:" + url);

        try {
            Map<String, Object> params = new HashMap<String, Object>();//目前对于damengxiang.me这个网站的请求，这个参数没有用处
            String response = HttpConnectionUtils.getGetResult( url, params);

            JSONObject appJson = appCrawlerService.parseFromResponse(response);

            AppDTO appDTO = appCrawlerService.encapAppDTO(appJson);

            appCrawlerService.save(appDTO);

            return appDTO;
        }catch(Exception e){
            log.error("crawler app failed", e);
            //如果失败了需要回滚？把之前存入系统的previewDTOs也需要删除掉
        }
        return "crawl app failed";
    }

    @GetMapping("/crawlerList")
    @ResponseBody
    public Object crawlerList(String url){

        log.warn("url:" + url);

        List<String> appNames = new ArrayList<>();

        try {
            Map<String, Object> params = new HashMap<String, Object>();//目前对于damengxiang.me这个网站的请求，这个参数没有用处
            String response = HttpConnectionUtils.getGetResult( url, params);

            JSONArray appJsonArray = appCrawlerService.parseArrayFromResponse(response);

            JSONObject appJson = null;
            AppDTO appDTO =null;
            for(int i = 0; i < appJsonArray.size(); i++){
                appJson = (JSONObject) appJsonArray.get(i);
                appDTO = appCrawlerService.encapAppDTO(appJson);
                appNames.add(appDTO.getAppName());
                if(appDTO != null){
                    appCrawlerService.save(appDTO);
                }
            }

            return appNames;
        }catch(Exception e){
            log.error("crawler app failed", e);
            //如果失败了需要回滚？把之前存入系统的previewDTOs也需要删除掉
        }
        return "crawl appList failed";
    }


}
