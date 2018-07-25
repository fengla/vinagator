package com.navi.controller;

import com.navi.data.PageModel;
import com.navi.dto.AppDTO;
import com.navi.dto.BannerDTO;
import com.navi.dto.CtDTO;
import com.navi.dto.PreviewDTO;
import com.navi.service.AppService;
import com.navi.service.BannerService;
import com.navi.service.CtService;
import com.navi.service.PreviewService;
import com.navi.util.AppUtil;
import com.navi.util.FileUtil;
import com.navi.util.ImgUtil;
import com.navi.util.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private CtService ctService;

    @Autowired
    private AppService appService;

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private ImgUtil imgUtil;

    @Autowired
    private PreviewService previewService;

    @Autowired
    private BannerService bannerService;


    /**
     * 审核App ? 展示所有待审核的app信息，点击审核通过或者审核不通过等等，需要把二维码展示出来，方便我去扫描
     * 看来这里还是需要展示一个管理页面，还是离不开SpringMVC...
     */

    //todo: web端发布app

//    @GetMapping("/pubApp")
//    public ModelAndView launchActivity(ModelMap modelMap){
//        List<Ct> cts = new ArrayList<>();
//        Ct ct1 = new Ct();
//        ct1.setId(1l);
//        ct1.setName("视频");
//
//        Ct ct2 = new Ct();
//        ct2.setId(2l);
//        ct2.setName("游戏");
//
//        Ct ct3 = new Ct();
//        ct3.setId(3l);
//        ct3.setName("教育");
//
//        ModelAndView modelAndView = new ModelAndView("pubApp", "command", null);
//        modelAndView.addObject("cts", cts);
//
//        return modelAndView;
//    }
    @GetMapping("/pubApp")
    public String launchActivity(ModelMap modelMap){
        List<CtDTO> cts = new ArrayList<>();

        cts = ctService.findAll();

        //version1
        //ModelAndView modelAndView = new ModelAndView("pubApp", "command", null);
        //modelAndView.addObject("cts", cts);

        //version2
        modelMap.addAttribute("cts", cts);

        return "pubApp";
    }

    @ResponseBody
    @PostMapping("/doPubApp")
    public Object doPubApp(ModelMap modelMap, @RequestParam("iconFile") MultipartFile iconFile, @RequestParam("qrCodeFile") MultipartFile qrCodeFile,
                           @RequestParam("preview0") MultipartFile preview0,
                           @RequestParam("preview1") MultipartFile preview1,
                           @RequestParam("preview2") MultipartFile preview2,
                           @RequestParam("preview3") MultipartFile preview3,
                           @RequestParam("preview4") MultipartFile preview4,
                           ModelMap model, String name,
                          String details, int ct, int sct){//todo：这个modelMap需要吗？（猜想：因为前台展示的数据是存在modelMap中的，所以应该还是必须的。。。）

        AppDTO appDTO;
        try {
            log.warn(String.format("ct:%d, sct:%d", ct, sct));

            //因为采用了富文本编辑器，所以这里已经不需要再做空格换行替换之类的操作了
//            log.warn("before+details:" + details);
//            details = details.replaceAll("\r\n","<br>");
//            log.warn("after+details:" + details);
            //tips = tips.replaceAll("\r\n","<br>");
            appDTO = new AppDTO(name, details);

            long appid = appUtil.genAppid();
            appDTO.setAppid(appid);

            appDTO.setCt(ct);
            if(sct != 0){
                appDTO.setSct(sct);
            }

            String uploadPath = context.getRealPath("") + "files" + File.separator;
            //String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            //todo:为什么下面这个log无法识别？
            log.warn("uploadPath:" + uploadPath);

            String iconUploadPath = uploadPath + "icons" + File.separator;
            String qrcodeUploadPath = uploadPath + "qrcodes" + File.separator;

            //String iconName = iconFile.getOriginalFilename();
            String suffix = "";
            String[] tmp = null;

            tmp = iconFile.getOriginalFilename().split("\\.");
            if(tmp.length > 1){
                suffix = "." + tmp[1];
            }else{
                log.warn("no suffix of iconFile");
            }
            String iconName = imgUtil.genIconid(suffix);
            FileCopyUtils.copy(iconFile.getBytes(), new File(iconUploadPath + iconName +suffix));
            appDTO.setIcon("/files/icons/" + iconName + suffix);//todo: iconName其实也就是数据库中的icon id

            //String qrCodeName = qrCodeFile.getOriginalFilename();
            suffix = "";
            tmp = qrCodeFile.getOriginalFilename().split("\\.");
            if(tmp.length > 1){
                suffix = "." + tmp[1];
            }else{
                log.warn("no suffix of qrCodeFile");
            }
            String qrCodeName = imgUtil.genQrcodeid(suffix);
            FileCopyUtils.copy(qrCodeFile.getBytes(), new File(qrcodeUploadPath + qrCodeName + suffix));
            appDTO.setQrCode("/files/qrcodes/" + qrCodeName + suffix);

            //todo: 预览图0 ～ 4
            List<PreviewDTO> previewDTOS = new ArrayList<>();
            List<String> previews = new ArrayList<>();

            List<MultipartFile> multipartFiles = new ArrayList<>();
            multipartFiles.add(preview0);
            multipartFiles.add(preview1);
            multipartFiles.add(preview2);
            multipartFiles.add(preview3);
            multipartFiles.add(preview4);//todo:这样的代码好像把空的也给上传了？这样会有问题的？？？怎么办how to solve this problem ?
            String previewUploadPath = uploadPath + "previews" + File.separator;
            String previewName = "";
            for(MultipartFile cur : multipartFiles){
                if(!cur.isEmpty()){
                    suffix = "";
                    tmp = cur.getOriginalFilename().split("\\.");
                    if(tmp.length > 1){
                        suffix = "." + tmp[1];
                    }else{
                        log.warn("no suffix of previewFile");
                    }

                    if(suffix!=null && !"".equals(suffix)){
                        previewName = imgUtil.genPreviewid();
                        //上传文件需要保留原始后缀名
                        //todo: !!!重要!!! 关于后缀名，这里可以对后缀名进行校验，只允许上传有效的后缀名，防止用户直接上传sql,网页，脚本等；
                        FileCopyUtils.copy(cur.getBytes(), new File(previewUploadPath + previewName + suffix));

                        previews.add("/files/previews/" + previewName + suffix);
                        previewDTOS.add(new PreviewDTO(previewName));
                    }
                }
            }
            appDTO.setPreviews(previews);
            JsonSerializer jsonFilter = new JsonSerializer();
            String previewStr = jsonFilter.toJson(previews);//序列化
            appDTO.setPreviewStr(previewStr);

            previewService.save(previewDTOS);//重载的方法
            log.warn("[debug]appName:" + name + "previewDTOS:" + previewDTOS);
            //todo: 预览图end


            long updateDate = System.currentTimeMillis()/1000;//s
            appDTO.setUpdateDate(updateDate);

            log.warn("appDTO:" + appDTO);

            appDTO = appService.save(appDTO);//注意这里内外层的返回值不都是void,还可以是AppDTO

        }catch(IOException e){
            log.error("pubApp failed...");
            return "pubAppFailed";//返回到这个页面提示发布失败，以及详细信息，随后6s后自动跳转活动布页面（用户也可以点击立即跳转）
        }

        return appDTO;//发布后进入app详情页面，但是这里应该标识出"待审核"
    }

    @GetMapping("/crawlApp")
    public String crawlApp(ModelMap modelMap){
        return "AppCrawler";
    }//这里进入填写地址页面，然后请求CrawlerController


    @GetMapping("/editApp")
    public String editApp(ModelMap model, int curPage){
        //先把查询所有文章的逻辑写好+分页展示
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;
        Page<AppDTO> appDTOPage = appService.findApps(curPage, direction, sortProperty);//查询所有文章，指定页的文章（所有文章按照插入顺序的倒序来排列）

        List<CtDTO> cts = ctService.findAll();

        //设置ctName
        for(AppDTO appDTO : appDTOPage.getContent()){
            if(appDTO.getCt() != 0){
                for(CtDTO ctDTO : cts){
                    if(ctDTO.getId() == appDTO.getCt()){//todo: ctid与appDTO中的ct属性不一样啊，一个是long 一个是int，这个需要统一一下
                        appDTO.setCtName(ctDTO.getName());//todo: 这个在前端有什么作用？是不是可以优化？
                        break;
                    }
                }
            }else{
                appDTO.setCtName("未设置");
            }
        }

        model.addAttribute("resp", appDTOPage);
        model.addAttribute("cts", cts);
        return "AppEdit";
    }

    @ResponseBody
    @GetMapping("/listScts")
    public Object listScts(String ctid){//这里拿到的都是ct的id；并不是中文名

        log.warn("ctid:" + ctid);

        Map<Long, String> scts = new HashMap<>();

        return scts;
    }

//    @ResponseBody
//    @GetMapping("/validAppid")
//    public Object validAppid(String id){//这里拿到的都是ct的id；并不是中文名
//        //appUtil如果在这里不用注入的方式，直接在本类中进行new AppUtil()会发现appUtil中的appService没有注入成功？why?
//        long appid = Long.parseLong(id);
//        log.warn("appid:" + appid);
//        boolean valid = appUtil.checkValid(appid);
//        return valid;
//    }

    //todo: 审核只需要ajax执行，然后重新加载这个控件的值就好了，不需要重新加载整个页面
    @GetMapping("/auditApp")
    @ResponseBody
    public Object auditApp(String appid, boolean valid){

        log.info("auditApp, appid:" + appid + ", valid:" + valid);
        //更新字段appDTO
        boolean res = appService.auditByAppid(valid, Long.parseLong(appid));//todo: appid还是需要修改成String,不能是现在这样的long

        return res;
    }

    @GetMapping("/updateCt")
    @ResponseBody
    public Object updateCt(String appid, int ct){
        //todo:对于没有设置的前端拿到的默认值到底是null还是0呢？？？？？因为是int，所以应该是0的吧，应该是不存在null值的
        log.info("updateCt, appid:" + appid + ", ct:" + ct);//这里是新的分类
        //更新字段appDTO
        boolean res = appService.updateCtByAppid(ct, Long.parseLong(appid));//todo: appid还是需要修改成String,不能是现在这样的long

        return res;
    }

    @GetMapping("/deleteApp")
    @ResponseBody
    public Object deleteApp(String appid){

        log.info("deleteApp, appid:" + appid);
        //更新字段appDTO

        //删除的时候不仅仅需要删除appDTO表中的数据，还要把这个app关联的其他表中的数据都删除掉

        return "暂未上线";
    }

    //这个需要跳转到编辑app数据的页面中。。。
    //detail是不是得用之前的那个富文本控件来做
    //这个页面应该与发布页面是一样的，只不过进入页面的时候需要吧服务端的appDTO数据传过来并展示
    //保存的时候执行update语句而不是insert

    //todo: 这个不要ResponseBody因为是返回真实jsp页面
    @GetMapping("/doEditApp")
    public Object editApp(String appid){


        return "doEditApp";
    }


    @GetMapping("/editBanner")
    public String editBanner(ModelMap model, int curPage){
        //先把查询所有文章的逻辑写好+分页展示
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;
        Page<BannerDTO> bannerDTOPage = bannerService.findBanners(curPage, direction, sortProperty);//查询所有文章，指定页的文章（所有文章按照插入顺序的倒序来排列）

        model.addAttribute("resp", bannerDTOPage);

        return "BannerEdit";
    }

    @PostMapping("/updateBanner")
    @ResponseBody
    public String updateBanner(ModelMap model, String name, String remark){//若干参数，还需要接收图片？可以判断用户到底有没有新上传图片。。
        //todo
        //因为数据量本身就不大，所以完全可以每次更新一条记录中的所有字段，不用特别识别到底修改哪个字段再指定更新某个字段

        return "success";
    }

    @GetMapping("/delBanner")
    @ResponseBody
    public String delBanner(long bannerid){

        try {
            //1.删除文件系统中存储的图片
            //FileUtil.deleteFile(filePath);
            BannerDTO bannerDTO = bannerService.findById(bannerid);
            String rawPath = bannerDTO.getImg();
            FileUtil.deleteFile(rawPath);//todo： 这样应该不可以吧？毕竟还是需要处理一下相对路径？？？


            //2.删数据表中的记录
            bannerDTO = bannerService.deleteById(bannerid);
        }catch(Exception e){
            log.error("deleteBanner failed, detail:", e);
            return "failed";
        }

        return "success";
    }

    @GetMapping("/editCt")
    public String editCt(ModelMap model, int curPage){
        //先把查询所有文章的逻辑写好+分页展示
        String sortProperty = "updateDate";
        Sort.Direction direction = Sort.Direction.DESC;
        Page<CtDTO> ctDTOPage = ctService.findCts(curPage, direction, sortProperty);//查询所有文章，指定页的文章（所有文章按照插入顺序的倒序来排列）


        model.addAttribute("resp", ctDTOPage);

        return "CtEdit";
    }





}
