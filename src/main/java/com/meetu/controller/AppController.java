package com.meetu.controller;

import com.meetu.data.App;
import com.meetu.data.CommentDetail;
import com.meetu.dto.ActivityDTO;
import com.meetu.dto.AppDTO;
import com.meetu.dto.CommentDTO;
import com.meetu.dto.UserDTO;
import com.meetu.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
public class AppController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private AppService appService;


    /**
     * 查询app详细信息
     */
    @GetMapping("/appDetail")
    public Object showAppDetail(Long appId){

        //debug
        log.warn("enter get appDetail by appId controller, appId:" + appId);
        //todo:关键是这里接收appId不能错了。。。这里是这个方法的核心。。。
        AppDTO appDTO = new AppDTO();
        appDTO = appService.findById(appId);

        return appDTO;
    }

    /**
     * 查询全局热门app列表
     */
    @GetMapping("/showHotApps")
    public Object showHotApps(String ct){//这里拿到的都是ct的id；并不是中文名

        //debug
        //这里先不带参数，把程序调试通了再说
        log.warn("enter get Hotapps by ct controller, ct:" + ct);


        List<AppDTO> apps = new ArrayList<>();
        apps = appService.findAll();

        log.warn(apps.toString());


        return apps;
    }

    /**
     * 查询最新app列表
     */
    @Deprecated
    @GetMapping("/getLatestAppsOld")
    public Object getLatestAppsOld(){

        //debug
        //这里先不带参数，把程序调试通了再说
        log.warn("enter get LatestApps");


        List<AppDTO> apps = new ArrayList<>();
        apps = appService.findLatestAppsOld();

        log.warn(apps.toString());


        return apps;
    }

    @GetMapping("/getLatestApps")
    public Object getLatestApps(int curPage){

        log.warn("enter showLastApps, curPage:" + curPage);

        Page<AppDTO> apps = null;

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = appService.findApps(curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;
    }

    /**
     * 分页查询指定分类下的app
     * @param ct
     * @param curPage
     * @return
     */
    @GetMapping("/getAppsByCT")
    public Object getAppsByCT(int ct, int curPage){
        //todo:
        log.warn("enter showAppsByCT, curPage:" + curPage + ", ct:" + ct);

        Page<AppDTO> apps = null;

        //todo: 现在的冷启动策略就展示每个类别的最新更新的app吧。后续再调整排序策略（权重进行，综合：更新时间，关注人数，编辑评分等）

        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        apps = appService.findAppsByCT(ct, curPage, direction, sortProperty);

        log.warn(apps.toString());

        return apps;
    }

    /**
     * app信息上传（怎么上传文件？并且识别成path并在这里进行赋值？）
     */


    /**
     * 首页一个固定区域用来展示最新审核通过的app(最新app)
     */


    /**
     * 还需要做一个搜索的功能。。。不能搜索俨然是不正确的
     */




}
