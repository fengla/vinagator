package com.meetu.controller;

import com.meetu.dto.AppDTO;
import com.meetu.service.*;
import com.meetu.util.JsonFieldFilter;
import com.meetu.util.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
public class AppController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private AppService appService;


    /**
     * 查询app详细信息
     */
    @GetMapping("/appDetail")
    @JsonFieldFilter(type = AppDTO.class,exclude = "previewStr")//把gitPassword字段过滤掉
    public Object showAppDetail(Long appId){

        //debug
        log.warn("enter get appDetail by appId controller, appId:" + appId);
        //todo:关键是这里接收appId不能错了。。。这里是这个方法的核心。。。
        AppDTO appDTO = new AppDTO();
        appDTO = appService.findById(appId);

        //反序列化。。previews
        if(appDTO!=null && appDTO.getPreviewStr()!=null && !"".equals(appDTO.getPreviewStr())){
            try {
                JsonSerializer jsonFilter = new JsonSerializer();
                List<String> previews = jsonFilter.mapper.readValue(appDTO.getPreviewStr(), List.class);
                appDTO.setPreviews(previews);
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }

        log.warn("select appDTO by appid, details:" + appDTO);

        return appDTO;
    }

    /**
     * 查询全局热门app列表 todo: 后期改成真实查询热门app + 分页查询
     */
    @GetMapping("/showHotApps")
    @ResponseBody
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
     * 搜索app(以用户提供的关键词在title, sumamry中进行模糊匹配), 分页展示
     * @param keyword
     * @param curPage
     * @return
     */
    @GetMapping("/searchApps")
    public Object searchApps(String keyword, int curPage){
        //todo:
        log.warn("enter searchApps, curPage:" + curPage + ", keyword:" + keyword);

        Page<AppDTO> apps = null;

        //todo: 对于搜索接口而言，每页应该展示几个呢？？？

        //搜索结果的排序策略：标题匹配的优先展示，标题没有匹配上的摘要匹配上的第二优先级展示，同一个优先级中怎么排序呢？（对于ofo小黄车，摩拜这样子本身不含有关键词"共享单车"的，用户搜索时候应该怎么展示呢？）
        //最新app = 按照更新时间排序降序的最近几个app
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;//降序
        //todo:搜索接口
        apps = appService.searchApps(keyword, curPage, direction, sortProperty);

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
