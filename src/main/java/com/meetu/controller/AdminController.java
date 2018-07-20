package com.meetu.controller;

import com.meetu.dto.AppDTO;
import com.meetu.dto.CtDTO;
import com.meetu.dto.PreviewDTO;
import com.meetu.service.AppService;
import com.meetu.service.CtService;
import com.meetu.service.PreviewService;
import com.meetu.util.AppUtil;
import com.meetu.util.ImgUtil;
import com.meetu.util.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ResponseBody
    @GetMapping("/listScts")
    public Object listScts(String ctid){//这里拿到的都是ct的id；并不是中文名

        log.warn("ctid:" + ctid);

        Map<Long, String> scts = new HashMap<>();

        return scts;
    }

    @ResponseBody
    @GetMapping("/validAppid")
    public Object validAppid(String id){//这里拿到的都是ct的id；并不是中文名
        //appUtil如果在这里不用注入的方式，直接在本类中进行new AppUtil()会发现appUtil中的appService没有注入成功？why?
        long appid = Long.parseLong(id);
        log.warn("appid:" + appid);
        boolean valid = appUtil.checkValid(appid);
        return valid;
    }









}
