package com.meetu.controller;

import com.meetu.data.Ct;
import com.meetu.dto.AppDTO;
import com.meetu.dto.CtDTO;
import com.meetu.service.ActivityService;
import com.meetu.service.AppService;
import com.meetu.service.CtService;
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
    public Object doLogin(ModelMap modelMap, @RequestParam("iconFile") MultipartFile iconFile, @RequestParam("qrCodeFile") MultipartFile qrCodeFile, ModelMap model, String name,
                          String details, int ct, int sct){//todo：这个modelMap需要吗？（猜想：因为前台展示的数据是存在modelMap中的，所以应该还是必须的。。。）

//        log.warn("details2:"+details2);

        AppDTO appDTO;
        try {
            log.warn(String.format("ct:%d, sct:%d", ct, sct));


            //todo: 2018-04-06
            //TODO: 怎么接收前端传来的数据呢？？？String name,String location看看这样能不能接收到数据呢？??
            //TODO:现在看来文件上传是没有问题了，就看看怎么提交表单？？？
            //todo: 搜索spriingBoot/springMVC提交表单
            log.warn("before+details:" + details);
            details = details.replaceAll("\r\n","<br>");
            log.warn("after+details:" + details);
            //tips = tips.replaceAll("\r\n","<br>");
            appDTO = new AppDTO(name, details);

            appDTO.setCt(ct);
            if(sct!=0){
                appDTO.setSct(sct);
            }

            //todo:
            //怎么传userid进来呢？
            //设置activityId为"201803011526（yyyymmddhhmm）+userid"(userid八位数字，整个id用String表示)
            //todo:end
            //log.warn("enter doLaunchActivity, activity:" + activity);
            String uploadPath = context.getRealPath("") + "files" + File.separator;
            //String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            //todo:为什么下面这个log无法识别？
            log.warn("uploadPath:" + uploadPath);
            System.out.println("uploadPath:" + uploadPath);
            //Now do something with file...
            String iconUploadPath = uploadPath + "icons" + File.separator;
            String qrcodeUploadPath = uploadPath + "qrcodes" + File.separator;
            FileCopyUtils.copy(iconFile.getBytes(), new File(iconUploadPath + iconFile.getOriginalFilename()));

            appDTO.setIcon("/files/icons/" + iconFile.getOriginalFilename());

            FileCopyUtils.copy(qrCodeFile.getBytes(), new File(qrcodeUploadPath + qrCodeFile.getOriginalFilename()));

            appDTO.setQrCode("/files/qrcodes/" + qrCodeFile.getOriginalFilename());

            //当前日期
            //数据库中还是存储时间戳吧
            long updateDate = System.currentTimeMillis()/1000;//s
            log.warn("updateDate:" + updateDate);
            appDTO.setUpdateDate(updateDate);
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
//            String updateDate = sdf.parse(String.valueOf(System.currentTimeMillis()));




            log.warn("appDTO:" + appDTO);
            //todo:下面开始解析文件，存mongo

            //todo:稍后放出这个存储的业务逻辑。。。
            appDTO = appService.save(appDTO);//注意这里内外层的返回值不都是void,还可以是AppDTO


            //String fileName = iconFile.getOriginalFilename();

            //成功才需要这么设置，失败了就不需要设置了
            //model.addAttribute("activity", activityDTO);

            //这里加载评论区。。。点击活动详情时候也是这么展示：先从数据库中检索一下此活动id关联的评论，然后便利展示
            //model.addAttribute("comments", null);
        }catch(IOException e){
            log.error("pubApp failed...");
            return "pubAppFailed";//返回到这个页面提示发布失败，以及详细信息，随后6s后自动跳转活动布页面（用户也可以点击立即跳转）
        }


        return appDTO;//发布活动后进入app详情页面，但是这里应该标识出"待审核"

    }

    @ResponseBody
    @GetMapping("/listScts")
    public Object listScts(String ctid){//这里拿到的都是ct的id；并不是中文名

        log.warn("ctid:" + ctid);

//        List<Sct> scts = new ArrayList<>();
//        Sct sct1 = new Sct();
//        sct1.setId(1l);
//        sct1.setName("二级分类1");
//        Sct sct2 = new Sct();
//        sct2.setId(2l);
//        sct2.setName("二级分类2");
//        Sct sct3 = new Sct();
//        sct3.setId(3l);
//        sct3.setName("二级分类3");
//        scts.add(sct1);
//        scts.add(sct2);
//        scts.add(sct3);
        Map<Long, String> scts = new HashMap<>();
//        scts.put(1l, "二级分类1");
//        scts.put(2l, "二级分类2");
//        scts.put(3l, "二级分类3");

        //todo: 不用Map,直接返回对象，前端可以处理吗？



        return scts;
    }







}
